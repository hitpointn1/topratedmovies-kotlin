package com.example.topratedmovies.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.topratedmovies.R
import com.example.topratedmovies.models.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class TopRatedMoviesAdapter(
    private var movies: MutableList<Movie>
) : RecyclerView.Adapter<TopRatedMoviesAdapter.TopRatedMoviesViewHolder>() {
    class TopRatedMoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie,
            parent,
            false
        )
        return TopRatedMoviesViewHolder(itemView)
    }

    override fun getItemCount() = movies.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {
        val currentMovie = movies[position]
        holder.itemView.rvMoviePosition.text = "${position + 1}."
        holder.itemView.rvMovieTitle.text = currentMovie.title
        holder.itemView.setOnClickListener {
            Log.d("MyLog", "Item ID is ${currentMovie.id}")
            //val intent = Intent(holder.itemView.context, NextActivity::class.java)
            //intent.putExtra("id", currentMovie.id)
            //startActivity(intent)
        }
    }

    fun appendMovies(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
}