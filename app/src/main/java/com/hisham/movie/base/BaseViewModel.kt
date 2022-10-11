package com.hisham.movie.base

import androidx.lifecycle.ViewModel
import com.hisham.movie.pojo.movie.MovieDetailResponse
import com.hisham.movie.pojo.movie.MovieSearchResponse
import com.hisham.movie.utils.Event


abstract class BaseViewModel : ViewModel()


data class MovieDataState(
    val showProgress : Boolean,
    val data : Event<MovieSearchResponse>?,
    val error : Event<Int>?
)

data class MovieDetailDataState(
    val showProgress : Boolean,
    val data : Event<MovieDetailResponse>?,
    val error : Event<Int>?
)

