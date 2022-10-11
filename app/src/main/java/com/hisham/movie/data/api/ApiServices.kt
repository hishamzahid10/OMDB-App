package com.hisham.movie.data.api

import com.hisham.movie.data.api.ApiKeys.REQUEST_CONTENT
import com.hisham.movie.pojo.movie.MovieDetailResponse
import com.hisham.movie.pojo.movie.MovieSearchResponse
import retrofit2.http.*


interface MovieApiServices {

    @GET(value = REQUEST_CONTENT)
    suspend fun getAllMovies(@Query(value = ApiKeys.SEARCH_CONST) search: String?) : MovieSearchResponse


    @GET(value = ApiKeys.REQUEST_DETAIL)
    suspend fun getMovieDetail(@Query(value = ApiKeys.ID_CONST) id: String?) : MovieDetailResponse
}