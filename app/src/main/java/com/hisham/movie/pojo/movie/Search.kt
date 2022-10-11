package com.hisham.movie.pojo.movie

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Search")
data class Search(
    @ColumnInfo(name = "Poster")
    var Poster: String?,

    @ColumnInfo(name = "Title")
    var Title: String?,

    @ColumnInfo(name = "Type")
    var Type: String?,

    @ColumnInfo(name = "Year")
    var Year: String?,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "imdbID")
    var imdbID: String
)