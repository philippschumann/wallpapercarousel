package com.philippschumann.wallpapercarousel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.philippschumann.wallpapercarousel.database.CarouselDatabase
import com.philippschumann.wallpapercarousel.database.CarouselRepository
import com.philippschumann.wallpapercarousel.model.Carousel
import com.philippschumann.wallpapercarousel.model.CarouselWithImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarouselOverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CarouselRepository
    val allCarousels: LiveData<List<CarouselWithImages>>

    init {
        val carouselDao = CarouselDatabase.getDatabase(
            application.applicationContext,
            viewModelScope
        ).carouselDao()
        repository = CarouselRepository(carouselDao)
        allCarousels = repository.allCarousels
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(carousel: Carousel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(carousel)
    }
}