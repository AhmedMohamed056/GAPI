package com.kotlinlearn.android5.datasource.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitConfig {
    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getMovieApiServiceInstance(): MovieApiService{
        return getRetrofitInstance().create<MovieApiService>()
    }
}