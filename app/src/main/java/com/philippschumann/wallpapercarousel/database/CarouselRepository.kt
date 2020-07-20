package com.philippschumann.wallpapercarousel.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.philippschumann.wallpapercarousel.database.dao.CarouselDao
import com.philippschumann.wallpapercarousel.database.model.Carousel
import com.philippschumann.wallpapercarousel.database.model.CarouselWithImages
import com.philippschumann.wallpapercarousel.database.model.Image

class CarouselRepository(private val carouselDao: CarouselDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCarousels: LiveData<List<CarouselWithImages>> = carouselDao.getCarouselsWithImages()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(carousel: Carousel) {
        carouselDao.insert(carousel)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertImage(image: Image) {
        carouselDao.insert(image)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteCarousels() {
        carouselDao.deleteCarousels()
    }
}