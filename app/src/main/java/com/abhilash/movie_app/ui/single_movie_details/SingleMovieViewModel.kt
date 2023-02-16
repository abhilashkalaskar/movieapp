package com.abhilash.movie_app.ui.single_movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.abhilash.movie_app.data.model.MovieDetails
import com.abhilash.movie_app.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieRepository : MovieDetailesRepository, movieId:Int): ViewModel(){

    private val compositeDisposable =CompositeDisposable()

    val movieDetails :LiveData<MovieDetails>by lazy{
        movieRepository.fetchSingleMovieDetails(compositeDisposable,movieId)
    }

    val networkState :LiveData<NetworkState>by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }
}