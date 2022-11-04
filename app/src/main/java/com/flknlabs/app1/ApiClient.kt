package com.flknlabs.app1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"
//const val API_KEY = "aca4d12980ec55a45582f95295b90323"
const val IMAGE_BASE_URL_H = "https://www.themoviedb.org/t/p/w533_and_h300_bestv2/"
const val IMAGE_BASE_URL_V = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/"

class ApiClient {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var movieDatabaseAPI: ApiService = retrofit.create(ApiService::class.java)



}