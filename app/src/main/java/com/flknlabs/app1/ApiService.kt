package com.flknlabs.app1


import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{id}")
    fun getMovies(@Path("id") id: Int, @Query("api_key") apiKey: String): Observable<Response<BaseResponse>>
}