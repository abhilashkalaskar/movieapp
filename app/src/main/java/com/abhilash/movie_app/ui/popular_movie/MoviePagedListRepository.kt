package com.abhilash.movie_app.ui.popular_movie
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.abhilash.movie_app.data.api.MovieDBInterface
import com.abhilash.movie_app.data.api.POST_PER_PAGE
import com.abhilash.movie_app.data.model.Movie
import com.abhilash.movie_app.data.repository.MovieDataSource
import com.abhilash.movie_app.data.repository.MovieDataSourceFactory
import com.abhilash.movie_app.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository (private val apiService : MovieDBInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }
}