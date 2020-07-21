package com.philippschumann.wallpapercarousel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.philippschumann.wallpapercarousel.database.CarouselDatabase
import com.philippschumann.wallpapercarousel.database.CarouselRepository
import com.philippschumann.wallpapercarousel.database.model.Carousel
import com.philippschumann.wallpapercarousel.database.model.CarouselWithImages
import com.philippschumann.wallpapercarousel.database.model.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CarouselRepository
    val selected = MutableLiveData<CarouselWithImages>()
    val allCarousels: LiveData<List<CarouselWithImages>>

    init {
        val carouselDao = CarouselDatabase.getDatabase(
            application.applicationContext,
            viewModelScope
        ).carouselDao()
        repository = CarouselRepository(carouselDao)
        deleteCarousels()
        val carousel: Carousel = Carousel()
        carousel.carouselId = 1
        insert(carousel)
        val image: Image = Image("test", carousel.carouselId)
        insertImage(image)
        allCarousels = repository.allCarousels
    }

    fun select(carousel: CarouselWithImages) {
        selected.value = carousel
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(carousel: Carousel) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(carousel)
    }

    fun insertImage(image: Image) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertImage(image)
    }

    fun deleteCarousels() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteCarousels()
    }
}