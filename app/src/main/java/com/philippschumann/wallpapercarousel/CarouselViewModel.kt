package com.philippschumann.wallpapercarousel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.philippschumann.wallpapercarousel.database.CarouselDatabase
import com.philippschumann.wallpapercarousel.database.CarouselRepository
import com.philippschumann.wallpapercarousel.model.Carousel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CarouselViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CarouselRepository

    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allCarousels: LiveData<List<Carousel>>

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