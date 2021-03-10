package com.example.topratedmovies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.topratedmovies.models.MovieDetailsById
import com.example.topratedmovies.services.DetailsActivityService
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailsActivity: AppCompatActivity() {

    private var detailsActivityService : DetailsActivityService = DetailsActivityService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        detailsActivityService.GetMovieDetailsById(getIntent().getLongExtra("id", 550), ::OnDetailsReceive, ::onTimeout)
    }

    private fun onTimeout() {
        runOnUiThread {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Error in the application")
                    .setMessage("Timeout!")
                    .setPositiveButton("Got it") {
                        dialog, id ->  dialog.cancel()
                        pbTopRatedMovies.visibility = View.INVISIBLE
                    }
            builder.create().show()
        }
    }

    @SuppressLint("SetTextI18n")
    fun OnDetailsReceive(textBody : String){
        val gson = GsonBuilder().create()
        val responseData = gson.fromJson<MovieDetailsById>(textBody, MovieDetailsById::class.java)

        runOnUiThread {
            pbTopRatedMoviesDetails.visibility = View.INVISIBLE
            tvMovieTitle.text = "${responseData.title} (Original Title: ${responseData.original_title})"
            tvMovieOriginal_language.text = "Original language of movie is ${responseData.original_language}"
            tvMoviePopularity.text = "Popularity of movie is ${responseData.popularity}"
            tvMovieRelease_date.text = "Release Date: ${responseData.release_date}"
            tvMovieBudget.text = "Budget is: $${responseData.budget}"

        }
    }
}