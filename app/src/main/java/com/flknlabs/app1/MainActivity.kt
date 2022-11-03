package com.flknlabs.app1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var tvTitle: TextView
    lateinit var tvOverview: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById<ImageView>(R.id.imgPoster)
        tvTitle = findViewById<TextView>(R.id.tvTitle)
        tvOverview = findViewById<TextView>(R.id.tvOverview)
        val btLoad = findViewById<Button>(R.id.btLoad)
        btLoad.setOnClickListener {
            loadRandomMovie()
        }
    }

    override fun onResume() {
        super.onResume()
        loadRandomMovie()
    }

    fun loadRandomMovie() {
        val movieId = (100..1000).random()
        request(movieId)
    }

    fun loadImage(imagePath: String) {
        Glide
            .with(this)
            .load(IMAGE_BASE_URL + imagePath)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(imageView)
    }

    fun loadTitle(title: String) {
        tvTitle.text = title
    }

    fun loadOverview(overview: String) {
        tvOverview.text = overview
    }

    fun request(movie: Int) {
        val apiClient = ApiClient()
        val call = apiClient.movieDatabaseAPI.getMovies(movie, BuildConfig.API_KEY)

        call.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                response.body()?.let { body ->
                    val json = Gson().toJson(body)
                    Log.d("MainActivity", "Response: $json")
                    loadImage(body.poster_path)
                    loadTitle(body.title)
                    loadOverview(body.overview)
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("MainActivity", "Error: ${t.stackTrace}}")
                loadImage("")
            }
        })
    }
}

