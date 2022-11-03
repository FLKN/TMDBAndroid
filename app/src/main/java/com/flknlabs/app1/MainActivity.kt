package com.flknlabs.app1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var overview : TextView
    lateinit var releaseDate : TextView
    lateinit var titulo : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        imageView = findViewById<ImageView>(R.id.imgPoster)
        overview = findViewById<TextView>(R.id.tvOverview)
        releaseDate = findViewById<TextView>(R.id.tvReleaseDate)
        titulo = findViewById<TextView>(R.id.tvTitulo)

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
                llenarInfo(response.body()?.overview?:"", response.body()?.release_date?:"", response.body()?.original_title?:"")
                llenarRatingBar(response.body()?.vote_average?:0.0)

            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("MainActivity","Error: ${t.stackTrace}}")
                loadImage( "")
            }
        })
    }

    private fun llenarRatingBar(voteAverage: Double) {
        val rvstar = findViewById<RatingBar>(R.id.rbStar)
        rvstar.rating = voteAverage.toFloat()
    }

    private fun llenarInfo(overview : String, releaseDate : String, title : String) {


        this.overview.text = overview
        this.releaseDate.text =  releaseDate
        this.titulo.text = title
    }
}