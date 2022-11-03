package com.flknlabs.app1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var imageBanner: ImageView
    lateinit var titleMovie: TextView
    lateinit var descriptionMovie: TextView
    lateinit var genres: TextView
    lateinit var releaseDate: TextView
    lateinit var language: TextView
    lateinit var popularity: TextView
    lateinit var voteAverage: TextView
    lateinit var duration: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById<ImageView>(R.id.imPosterDescription)
        imageBanner = findViewById<ImageView>(R.id.ivMovieBackDropDescription)
        titleMovie = findViewById<TextView>(R.id.tvTitleMovieDescription)
        descriptionMovie = findViewById<TextView>(R.id.tvOverviewMovieDescription)
        genres = findViewById<TextView>(R.id.tvGenresDescription)
        releaseDate = findViewById<TextView>(R.id.tvReleaseDateDescription)
        language = findViewById<TextView>(R.id.tvLanguageDescription)
        popularity = findViewById<TextView>(R.id.tvPopularityDescription)
        voteAverage = findViewById<TextView>(R.id.tvVoteAverageDescription)
        duration = findViewById<TextView>(R.id.tvDurationDescription)
    }

    override fun onResume() {
        super.onResume()
        request()
    }

    fun loadContent(response: BaseResponse?){
        response.apply {
            Glide.with(this@MainActivity)
                .load(IMAGE_BASE_URL + this?.poster_path)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(imageView)

            Glide.with(this@MainActivity)
                .load(IMAGE_BASE_URL + this?.backdrop_path)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(imageBanner)

            titleMovie.text = this?.title
            descriptionMovie.text = this?.overview

            var genresContainer = ""
            this?.genres?.forEach { genre ->
                genresContainer += "${genre.name}, "
            }
            genres.setText(genresContainer.dropLast(2))

            releaseDate.setText(this?.release_date)
            popularity.setText((this?.popularity).toString())
            language.setText(this?.original_language!!.uppercase())
            voteAverage.setText("${(DecimalFormat("#.#").format(this.vote_average))}/10")
            duration.setText(this.runtime!!.converterTime())
        }
    }

    fun Int.converterTime(): String{
        var horas: Int = 0
        var min: Int = this

        while (min>60){
            horas++
            min -= 60
        }
        return "${horas}:${min}"
    }

    fun request() {
        val apiClient = ApiClient()
        val call = apiClient.movieDatabaseAPI.getMovies(500, API_KEY)

        call.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val json = Gson().toJson(response.body())
                Log.d("MainActivity", "Response: $json")
                loadContent(response.body())
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("MainActivity","Error: ${t.stackTrace}}")
            }
        })
    }


}

