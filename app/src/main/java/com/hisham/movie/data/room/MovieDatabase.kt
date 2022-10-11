package com.hisham.movie.data.room

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.hisham.movie.pojo.movie.MovieSearchResponse
import com.hisham.movie.pojo.movie.Search


@Database(entities = arrayOf(Search::class), version = 1, exportSchema = false)
abstract class MovieDatabase  : RoomDatabase(){
    abstract fun movieDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDataBaseClient(context: Context) : MovieDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, MovieDatabase::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}