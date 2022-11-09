package com.flknlabs.app1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.util.Util

const val videoURL1 =
    "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4"
const val videoURL2 =
    "https://media.rawg.io/media/stories-640/5c1/5c1914a7f914e849e3417f79e1dd2b71.mp4"

class MainActivity : AppCompatActivity() {

    private lateinit var exoPlayerView: StyledPlayerView
    private var exoPlayer: ExoPlayer? = null

    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun initializePlayer() {
        exoPlayerView = findViewById(R.id.exoPlayerView)

        exoPlayer = ExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                exoPlayerView.player = exoPlayer
            }

        val item1 = MediaItem.fromUri(videoURL1)
        val item2 = MediaItem.fromUri(videoURL2)
        exoPlayer?.addMediaItem(item1)
        exoPlayer?.addMediaItem(item2)


        exoPlayer?.playWhenReady = playWhenReady
        exoPlayer?.seekTo(currentWindow, playbackPosition)
        exoPlayer?.prepare()

        exoPlayer?.play()
    }

    private fun releasePlayer() {
        exoPlayer?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentAdGroupIndex
            playWhenReady = this.playWhenReady
            release()
        }
        exoPlayer = null
    }
}

