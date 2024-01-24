package com.kotlinlearn.android5.datasource.remote

import com.kotlinlearn.android5.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {

    @GET("3/movie/popular")
    fun fetchMovie(@Query("api_key")key:String): Call<MovieResponse>
}