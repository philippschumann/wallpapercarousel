package com.philippschumann.wallpapercarousel.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.philippschumann.wallpapercarousel.dao.CarouselDao
import com.philippschumann.wallpapercarousel.model.Carousel

class CarouselRepository(private val carouselDao: CarouselDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Carousel>> = carouselDao.getCarousels()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(carousel: Carousel) {
        carouselDao.insert(carousel)
    }
}