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
    lateinit var tvPopularity : TextView
    lateinit var tvReleaseDate : TextView
    lateinit var tvOriginalLenguage : TextView
    lateinit var tvOriginalTitle : TextView
    lateinit var tvOverview : TextView
    lateinit var btnVote : Button
    lateinit var btnVote2 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById<ImageView>(R.id.imgPoster)
        tvTitle = findViewById<TextView>(R.id.textViewTitle)
        tvPopularity = findViewById<TextView>(R.id.textViewPopularity)
        tvReleaseDate = findViewById<TextView>(R.id.textViewReleaseDate)
        tvOriginalTitle = findViewById<TextView>(R.id.textViewOriginalTitle)
        tvOriginalLenguage = findViewById<TextView>(R.id.textViewOriginalLenguage)
        tvOverview = findViewById<TextView>(R.id.textViewOverview)
        btnVote = findViewById<Button>(R.id.buttonVotes)
        btnVote2 = findViewById<Button>(R.id.buttonVotes2)
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


                response.body()?.let { body ->
                    val json = Gson().toJson(body)
                    //Log.d("MainActivity", "Response: $json")
                    loadImage(body.poster_path ?: "")
                    tvTitle.text = body.title
                    tvPopularity.text = "Popularity: ${body.popularity}"
                    tvReleaseDate.text = "Release Date: ${body.release_date}"
                    tvOriginalTitle.text = "Original Title: ${body.original_title}"
                    tvOriginalLenguage.text = "Original Lenguage: ${body.original_language}"
                    tvOverview.text = response.body()?.overview
                    btnVote.text = response.body()?.vote_count.toString()
                    btnVote2.text = response.body()?.vote_average.toString()
                }
            }

            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                Log.d("MainActivity","Error: ${t.stackTrace}}")
                loadImage( "")
            }
        })
    }
}

