package com.flknlabs.app1

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    private fun request() {
        // Broadcast receiver que me de el estatus de la red
        // si hay

        val observable = getApiInstance().movieDatabaseAPI.getMovies(600, BuildConfig.API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())

        observable?.subscribe(getObserver())

        //si no hay


    }

    private fun getObserver():Observer<Response<BaseResponse>> {
        return object : Observer<Response<BaseResponse>> {
            override fun onComplete() {
                Log.d("MainActivity", "Termine el request")
            }

            override fun onSubscribe(d: Disposable) { }

            override fun onNext(response: Response<BaseResponse>) {
                val json = Gson().toJson(response.body())
                Log.d("MainActivity", "Response: $json")
                loadImage(response.body()?.poster_path ?: "")
            }

            override fun onError(e: Throwable) {
                Log.d("MainActivity", "Error: ${e.stackTrace}")
                loadImage("")
            }
        }
    }

    private fun getApiInstance(): ApiClient = ApiClient()
}