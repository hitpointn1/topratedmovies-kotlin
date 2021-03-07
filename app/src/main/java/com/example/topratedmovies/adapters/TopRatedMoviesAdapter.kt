package com.example.topratedmovies.adapters

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

    override fun onBindViewHolder(holder: TopRatedMoviesViewHolder, position: Int) {
        var currentMovie = movies[position];
        holder.itemView.rvMoviePosition.text = (position + 1).toString() + '.';
        holder.itemView.rvMovieTitle.text = currentMovie.title;
        holder.itemView.setOnClickListener {
            Log.d("MyLog", "Position is $position")
        }
    }

    fun setMovies(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }
}