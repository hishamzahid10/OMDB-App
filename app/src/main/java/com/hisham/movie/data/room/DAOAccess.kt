package com.hisham.movie.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.hisham.movie.pojo.movie.Search

@Dao
interface DAOAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(loginTableModel: ArrayList<Search>)


    @Query("SELECT * FROM SEARCH")
    fun getAllData() : LiveData<List<Search>>
}