package com.kotlinlearn.android5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.kotlinlearn.android5.databinding.MovieLayoutBinding
import com.kotlinlearn.android5.util.Constants
import com.squareup.picasso.Picasso
import com.kotlinlearn.android5.model.Movie

class MovieAdapter(private val context:Context): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var movieList:MutableList<Movie> = mutableListOf<Movie>()
    inner class MovieViewHolder(itemView:MovieLayoutBinding): RecyclerView.ViewHolder(itemView.root) {
        var moviePoster:ImageView = itemView.mIv
        var movieTitle:TextView = itemView.mTxt

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.movieTitle.text = movie.title
        Picasso.get().load("${Constants.IMAGES_PATH}${movie.poster_path}").into(holder.moviePoster)
        holder.movieTitle.setOnClickListener{
            goToMovieActivity(movie)
        }
        holder.moviePoster.setOnClickListener{
            goToMovieActivity(movie)
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }
    fun goToMovieActivity(movie:Movie){
        var intent = Intent(context, MovieActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(context, intent, Bundle.EMPTY)
    }

    fun setData(movieList: MutableList<Movie>){
        this.movieList.addAll(movieList)
        notifyDataSetChanged() //to notify adapter that new data change has been happened to adapt it
    }
}