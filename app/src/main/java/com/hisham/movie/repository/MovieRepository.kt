package com.hisham.movie.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.hisham.movie.data.room.MovieDatabase
import com.hisham.movie.pojo.movie.Search
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MovieRepository {

    companion object {

        var loginDatabase: MovieDatabase? = null

        var loginTableModel: LiveData<List<Search>>? = null

        fun initializeDB(context: Context) : MovieDatabase {
            return MovieDatabase.getDataBaseClient(context)
        }

        fun insertData(context: Context, searchList:ArrayList<Search>) {

            loginDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                loginDatabase!!.movieDao().insertData(searchList)
            }

        }

        fun getMovieList(context: Context) : LiveData<List<Search>>? {

            loginDatabase = initializeDB(context)

            loginTableModel = loginDatabase?.movieDao()?.getAllData()

            return loginTableModel
        }

    }
}