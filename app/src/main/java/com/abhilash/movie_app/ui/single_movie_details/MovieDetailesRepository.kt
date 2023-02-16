package com.abhilash.movie_app.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.abhilash.movie_app.data.api.MovieDBInterface
import com.abhilash.movie_app.data.model.MovieDetails
import com.abhilash.movie_app.data.repository.MovieDetailsNetworkDataSource
import com.abhilash.movie_app.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieDetailesRepository(private val apiService: MovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource
    fun fetchSingleMovieDetails(
        compositeDisposable: CompositeDisposable,
        movieId: Int
    ): LiveData<MovieDetails> {

        movieDetailsNetworkDataSource =
            MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadedMovieResponse
    }

    fun getMovieDetailsNetworkState():LiveData<NetworkState>{
        return movieDetailsNetworkDataSource.networkState
    }

}