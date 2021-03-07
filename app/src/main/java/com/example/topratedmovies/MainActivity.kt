package com.example.topratedmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topratedmovies.adapters.TopRatedMoviesAdapter
import com.example.topratedmovies.models.Movie
import com.example.topratedmovies.models.TopRatedMoviesResponse
import com.example.topratedmovies.services.IMDBService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var topRatedMoviesAdapter: TopRatedMoviesAdapter
    private var imdbService: IMDBService = IMDBService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imdbService.GetTopRatedFilms(OnMoviesReceive())
    }

    fun OnMoviesReceive(): Callback {
        var self = this;
        return object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.d("MyLog", "Failure")
            }

            override fun onResponse(call: Call, response: Response) {
                var bodyText = response?.body?.toString()
                Log.d("MyLog", bodyText.toString())
                var gson = GsonBuilder().create()

                var responseData = gson.fromJson<TopRatedMoviesResponse>(bodyText, TopRatedMoviesResponse::class.java)
                var dataList = mutableListOf<Movie>(Movie(123, "Pulp fiction"))
                topRatedMoviesAdapter = TopRatedMoviesAdapter(dataList)
                rvTopRatedMovies.adapter = topRatedMoviesAdapter
                rvTopRatedMovies.layoutManager = LinearLayoutManager(self)
                pbTopRatedMovies.visibility = View.INVISIBLE
                rvTopRatedMovies.visibility = View.VISIBLE
            }
        }
    }
}