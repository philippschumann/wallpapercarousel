package com.philippschumann.wallpapercarousel.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.philippschumann.wallpapercarousel.database.model.Carousel
import com.philippschumann.wallpapercarousel.database.model.CarouselWithImages
import com.philippschumann.wallpapercarousel.database.model.Image

@Dao
interface CarouselDao {
    @Transaction
    @Query("SELECT * FROM carousel")
    fun getCarouselsWithImages(): LiveData<List<CarouselWithImages>>

    @Query("SELECT * from carousel ORDER BY carouselId ASC")
    fun getCarousels(): LiveData<List<Carousel>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(carousel: Carousel)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(image: Image)

    /*@Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWithImages(carousel: Carousel, images: List<Image>)*/
    @Transaction
    @Query("DELETE FROM Carousel")
    suspend fun deleteCarousels()
}