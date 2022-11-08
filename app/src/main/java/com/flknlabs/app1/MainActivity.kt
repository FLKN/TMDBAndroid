package com.flknlabs.app1

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

const val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

class MainActivity : AppCompatActivity() {
    lateinit var btnPlay: ImageButton
    lateinit var btnPause: ImageButton
    lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnPlay = findViewById<ImageButton>(R.id.btnPlay)
        btnPause = findViewById<ImageButton>(R.id.btnPause)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

        btnPlay.setOnClickListener { playSong() }
        btnPause.setOnClickListener { pauseSong() }
    }

    private fun playSong() {
        try {
            mediaPlayer.setDataSource(audioUrl)

            mediaPlayer.prepare()

            mediaPlayer.start()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        Toast.makeText(applicationContext, "Audio started playing..", Toast.LENGTH_SHORT).show()
    }

    private fun pauseSong() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            Toast.makeText(applicationContext, "Audio has been  paused..", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(applicationContext, "Audio not played..", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()

            mediaPlayer.reset()

            mediaPlayer.release()
        } else {
            Toast.makeText(applicationContext, "Audio not played..", Toast.LENGTH_SHORT).show()
        }
    }
}

