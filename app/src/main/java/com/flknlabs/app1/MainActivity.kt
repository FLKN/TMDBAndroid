package com.flknlabs.app1

import android.os.Bundle
<<<<<<< Updated upstream
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
=======
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
>>>>>>> Stashed changes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

<<<<<<< Updated upstream
        imageView = findViewById<ImageView>(R.id.imgPoster)
    }

    override fun onResume() {
        super.onResume()
        request()
    }

    fun loadImage(imagePath: String) {
        Glide
            .with(this)
            .load(IMAGE_BASE_URL + imagePath)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(imageView)
    }

    fun request() {
        val apiClient = ApiClient()
        val call = apiClient.movieDatabaseAPI.getMovies(600, BuildConfig.API_KEY)

        call.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val json = Gson().toJson(response.body())
                Log.d("MainActivity", "Response: $json")
                loadImage(response.body()?.poster_path ?: "")
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("MainActivity","Error: ${t.stackTrace}}")
                loadImage( "")
            }
        })
=======
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
>>>>>>> Stashed changes
    }

}

