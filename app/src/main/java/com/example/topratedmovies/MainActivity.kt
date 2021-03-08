package com.example.topratedmovies

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topratedmovies.adapters.TopRatedMoviesAdapter
import com.example.topratedmovies.models.TopRatedMoviesResponse
import com.example.topratedmovies.services.MainActivityService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    private var mainActivityService: MainActivityService = MainActivityService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityService.GetTopRatedFilms(::OnMoviesReceive)
    }

    fun OnMoviesReceive(bodyText: String) {
        val self = this;
        val gson = GsonBuilder().create()
        val responseData = gson.fromJson<TopRatedMoviesResponse>(bodyText, TopRatedMoviesResponse::class.java)

        runOnUiThread {
            topRatedMoviesAdapter = TopRatedMoviesAdapter(responseData.results.toMutableList())
            rvTopRatedMovies.adapter = topRatedMoviesAdapter
            rvTopRatedMovies.layoutManager = LinearLayoutManager(self)
            pbTopRatedMovies.visibility = View.INVISIBLE
            rvTopRatedMovies.visibility = View.VISIBLE
        }
    }
}