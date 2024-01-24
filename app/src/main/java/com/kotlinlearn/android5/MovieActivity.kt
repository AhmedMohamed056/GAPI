package com.kotlinlearn.android5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlinlearn.android5.databinding.ActivityMovieBinding
import com.kotlinlearn.android5.model.Movie
import com.kotlinlearn.android5.util.Constants
import com.squareup.picasso.Picasso

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private lateinit var movie:Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        movie = intent.getSerializableExtra("movie") as Movie

        setView()
    }

    private fun setView() {
        Picasso.get().load("${Constants.IMAGES_PATH}${movie.backdrop_path}").into(binding.movieBackdrop)
        Picasso.get().load("${Constants.IMAGES_PATH}${movie.poster_path}").into(binding.moviePoster)
        binding.movieTitle.text = movie.title
        binding.movieOverview.text = movie.overview
        binding.rate.text = movie.vote_average.toString()
        binding.releaseDate.text = movie.release_date
    }
}