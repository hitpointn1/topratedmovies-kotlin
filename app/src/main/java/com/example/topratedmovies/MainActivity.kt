package com.example.topratedmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topratedmovies.adapters.TopRatedMoviesAdapter
import com.example.topratedmovies.models.Movie
import com.example.topratedmovies.services.IMBDService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    private lateinit var imdbService: IMBDService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dataList = mutableListOf<Movie>(Movie(123, "Pulp fiction"))
        topRatedMoviesAdapter = TopRatedMoviesAdapter(dataList)
        rvTopRatedMovies.adapter = topRatedMoviesAdapter;
        rvTopRatedMovies.layoutManager = LinearLayoutManager(this)
        pbTopRatedMovies.visibility = View.INVISIBLE;
        rvTopRatedMovies.visibility = View.VISIBLE;
    }
}