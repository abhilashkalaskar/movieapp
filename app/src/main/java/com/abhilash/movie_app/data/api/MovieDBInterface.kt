package com.abhilash.movie_app.data.api

import com.abhilash.movie_app.data.model.MovieDetails
import com.abhilash.movie_app.data.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBInterface {

    @GET("tv/{movie_id}")
    fun getMovieDetails(@Path("movie_id")id:Int) : Single<MovieDetails>

    @GET("tv/popular")
    fun getPopularMovie(@Query("page")page:Int) :Single<MovieResponse>

}