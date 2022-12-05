package com.flknlabs.app1

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.themoviedb.org/3/"
//const val API_KEY = "aca4d12980ec55a45582f95295b90323"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185/"

class ApiClient {
    private var httpBuilder: OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    private var retrofit: Retrofit = Retrofit.Builder()
        .client(httpBuilder)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var movieDatabaseAPI: ApiService = retrofit.create(ApiService::class.java)
}