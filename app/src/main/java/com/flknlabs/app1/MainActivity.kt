package com.flknlabs.app1

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var imagePoster: ImageView
    lateinit var imageBack: ImageView
    lateinit var tvTitle: TextView
    lateinit var tvDate: TextView
    lateinit var rateBar: RatingBar
    lateinit var tvGenres: TextView
    lateinit var tvResume: TextView
    lateinit var tvLanguages: TextView
    lateinit var tvHomepage: TextView
    lateinit var btnTrailer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        imagePoster = findViewById<ImageView>(R.id.imgPoster)
        imageBack = findViewById<ImageView>(R.id.imgBack)
        tvTitle = findViewById<TextView>(R.id.tvTitleMovie)
        tvDate = findViewById<TextView>(R.id.tvDateMovie)
        rateBar = findViewById<RatingBar>(R.id.rateBar)
        tvGenres = findViewById<TextView>(R.id.tvGenresMovie)
        tvResume = findViewById<TextView>(R.id.tvResumeMovie)
        tvLanguages = findViewById<TextView>(R.id.tvLanguagesMovie)
        tvHomepage = findViewById<TextView>(R.id.tvHomepageMovie)
        btnTrailer = findViewById<Button>(R.id.btnTrailer)
    }

    override fun onResume() {
        super.onResume()
        request()
    }

    fun loadImage(imagePath: String, imageView: ImageView, type: String = "V") {
        val urlBase = if (type == "V") IMAGE_BASE_URL_V else IMAGE_BASE_URL_H
        Glide
            .with(this)
            .load(urlBase + imagePath)
            .placeholder(R.mipmap.ic_launcher_round)
            .into(imageView)
    }

    private fun request() {
        val apiClient = ApiClient()
        val call = apiClient.movieDatabaseAPI.getMovies(436270, BuildConfig.API_KEY)

        call.enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val json = Gson().toJson(response.body())
                Log.d("MainActivity", "Response: $json")
                val movie = response.body()
                loadImage(movie?.poster_path ?: "", imagePoster)
                loadImage(movie?.backdrop_path ?: "", imageBack, "H")
                tvTitle.text = movie?.title
                tvDate.text = movie?.release_date
                rateBar.rating =
                    ((movie?.vote_average?.toFloat()?.times(5))?.div(10)) ?: 0f
                var genresMovie: String = ""
                movie?.genres?.forEach {
                    genresMovie += "${it.name}, "
                }
                tvGenres.text = genresMovie.substring(0, genresMovie.length - 2).plus(".")
                tvResume.text = movie?.overview
                var languagesMovie: String = ""
                movie?.spoken_languages?.forEach {
                    languagesMovie += "${it.english_name}, "
                }
                tvLanguages.text = languagesMovie.substring(0, languagesMovie.length - 2).plus(".")
                tvHomepage.text = movie?.homepage
                btnTrailer.setOnClickListener {
                    Toast.makeText(this@MainActivity, "Go see the trailer", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("MainActivity","Error: ${t.stackTrace}}")
                loadImage( "", imagePoster)
            }
        })
    }
}

