package com.philippschumann.wallpapercarousel.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.philippschumann.wallpapercarousel.model.Carousel

@Dao
interface CarouselDao {

    @Query("SELECT * from carousel_table ORDER BY id ASC")
    fun getCarousels(): LiveData<List<Carousel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(carousel: Carousel)

    @Query("DELETE FROM carousel_table")
    suspend fun deleteAll()
}