package com.flknlabs.app1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.bumptech.glide.Glide
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var movieTitle: TextView
    lateinit var description: TextView
    lateinit var info: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieTitle = findViewById(R.id.movie_title)
        imageView = findViewById(R.id.movie_poster)
        description = findViewById(R.id.movie_description)
        info = findViewById(R.id.movie_info)



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

    fun loadMovie(baseMovie: BaseResponse?){
        baseMovie?.let{
            loadImage(it.poster_path)
            movieTitle.text = it.title
            description.text = it.overview
            createMovieInfo(it)
        }?: run {

        }
    }

    fun createMovieInfo(movie: BaseResponse){
        info.text = buildSpannedString {
            bold{ append("Language: ")}
            appendLine(movie.original_language)

            bold{ append("Original title: ")}
            appendLine(movie.original_title)

            bold { append("Release date: ") }
            appendLine(movie.release_date)
        }
    }

    fun request() {
        val apiClient = ApiClient()
        val call = apiClient.movieDatabaseAPI.getMovies(2, "d70583e78c3473c035fe05dee3c7c8b8")

        call.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val json = Gson().toJson(response.body())
                Log.d("MainActivity", "Response: $json")
                //loadImage(response.body()?.poster_path ?: "")
                loadMovie(response.body())
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("MainActivity","Error: ${t.stackTrace}}")
                loadImage( "")
            }
        })
    }
}

