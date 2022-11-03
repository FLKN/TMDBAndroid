package com.flknlabs.app1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        request()
    }

    fun request() {
        val apiClient = ApiClient()
        val call = apiClient.movieDatabaseAPI.getMovies(600, BuildConfig.API_KEY)

        call.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val json = Gson().toJson(response.body())
                Log.d("MainActivity", "Response: $json")
                response.body()?.let { retrieverResponse(it) }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("MainActivity", "Error: ${t.stackTrace}}")
             }
        })
    }

    fun loadImage(imagePath: String, view: ImageView) {
        Glide
            .with(this)
            .load(IMAGE_BASE_URL + imagePath)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(view)
    }

    private fun retrieverResponse(response: BaseResponse) {
        val title = findViewById<TextView>(R.id.movie_title)
        val rating = findViewById<RatingBar>(R.id.movie_rating)
        val releaseDate = findViewById<TextView>(R.id.movie_release_date)
        val overView = findViewById<TextView>(R.id.movie_overview)
        val backdrop = findViewById<ImageView>(R.id.movie_backdrop)
        val poster = findViewById<ImageView>(R.id.movie_poster)
        title.text = response.title
        rating.rating = response.vote_average.toFloat() / 2
        releaseDate.text = response.release_date
        overView.text = response.overview
        loadImage(response.poster_path, poster)
        loadImage(response.backdrop_path, backdrop)
    }
}



