package com.philippschumann.wallpapercarousel.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.philippschumann.wallpapercarousel.model.Carousel
import com.philippschumann.wallpapercarousel.model.CarouselWithImages

@Dao
interface CarouselDao {
    @Transaction
    @Query("SELECT * FROM carousel_table")
    fun getCarouselsWithImages(): LiveData<List<CarouselWithImages>>

    @Query("SELECT * from carousel_table ORDER BY carouselId ASC")
    fun getCarousels(): LiveData<List<Carousel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(carousel: Carousel)

    @Query("DELETE FROM carousel_table")
    suspend fun deleteAll()
}