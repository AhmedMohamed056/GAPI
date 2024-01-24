package com.kotlinlearn.android5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlinlearn.android5.databinding.ActivityMainBinding
import com.kotlinlearn.android5.datasource.remote.RetrofitConfig
import com.kotlinlearn.android5.model.Movie
import com.kotlinlearn.android5.model.MovieResponse
import com.kotlinlearn.android5.util.Constants
import com.kotlinlearn.android5.util.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.progressBar.show()
            RetrofitConfig.getMovieApiServiceInstance()
                .fetchMovie(Constants.API_KEY)
                .enqueue(object : Callback<MovieResponse>{
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        binding.progressBar.hide()
                        if(response.code() == 200) {//you can replace the condition with response.isSuccessful
                            response.body()?.let {
                                viewMovies(it)
                            }
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        t.message?.let {
                            showToast(it)
                        }
                    }
                })
    }



    private fun viewMovies(it: MovieResponse) {
        val myRecyclerView = binding.recyclerView



        val adapter = MovieAdapter(this)
        val movieList = mutableListOf<Movie>()
        myRecyclerView.layoutManager = GridLayoutManager(this, 2)
        myRecyclerView.adapter = adapter

        it.results.forEach{movie -> movieList.add(movie as Movie)}
        adapter.setData(movieList)


    }
}