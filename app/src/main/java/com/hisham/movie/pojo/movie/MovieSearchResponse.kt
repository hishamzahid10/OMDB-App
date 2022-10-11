package com.hisham.movie.pojo.movie

data class MovieSearchResponse(
    var Response: String?,
    var Search: ArrayList<Search>?,
    var totalResults: String?
)