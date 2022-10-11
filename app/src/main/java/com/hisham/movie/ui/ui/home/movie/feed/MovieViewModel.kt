package com.hisham.movie.ui.ui.home.movie.feed

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.hisham.movie.R
import com.hisham.movie.base.BaseViewModel
import com.hisham.movie.base.MovieDataState
import com.hisham.movie.base.MovieDetailDataState
import com.hisham.movie.data.api.MovieApiServices
import com.hisham.movie.pojo.movie.MovieDetailResponse
import com.hisham.movie.pojo.movie.MovieSearchResponse
import com.hisham.movie.pojo.movie.Search
import com.hisham.movie.repository.MovieRepository
import com.hisham.movie.utils.Event
import kotlinx.coroutines.launch


class MovieViewModel(val serviceUtil: MovieApiServices) :BaseViewModel(){

    var liveDataLogin: LiveData<List<Search>>? = null

    fun insertData(context: Context,search: ArrayList<Search>) {
        MovieRepository.insertData(context,search)
    }

    fun getMovieList(context: Context) : LiveData<List<Search>>? {
        liveDataLogin = MovieRepository.getMovieList(context)
        return liveDataLogin
    }


    private val _uiState = MutableLiveData<MovieDataState>()  // 2
    val uiState: LiveData<MovieDataState> = _uiState


    private val _uiStateDetail = MutableLiveData<MovieDetailDataState>()  // 2
    val uiStateDetail: LiveData<MovieDetailDataState> = _uiStateDetail

    fun getAllMovies()
    {
        viewModelScope.launch {   // 4
            runCatching {  //  5
                emitUiState(showProgress = true)
                serviceUtil.getAllMovies("top")
            }.onSuccess {
                emitUiState(media = Event(it))
            }.onFailure {
                emitUiState(error = Event(R.string.internet_failure_error))
            }
        }
    }


    fun getMovieDetail(id:String)
    {
        viewModelScope.launch {   // 4
            runCatching {  //  5
                emitUiStateDetail(showProgress = true)
                serviceUtil.getMovieDetail(id)
            }.onSuccess {
                emitUiStateDetail(media = Event(it))
            }.onFailure {
                emitUiStateDetail(error = Event(R.string.internet_failure_error))
            }
        }
    }

    private fun emitUiState(
        showProgress : Boolean = false,
        media: Event<MovieSearchResponse>? = null,
        error : Event<Int>? = null
    ) {
        val dataState = MovieDataState(showProgress, media, error)
        _uiState.value = dataState
    }

    private fun emitUiStateDetail(
        showProgress : Boolean = false,
        media: Event<MovieDetailResponse>? = null,
        error : Event<Int>? = null
    ) {
        val dataState = MovieDetailDataState(showProgress, media, error)
        _uiStateDetail.value = dataState
    }

}