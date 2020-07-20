package com.philippschumann.wallpapercarousel

import com.philippschumann.wallpapercarousel.database.model.CarouselWithImages

interface CarouselOverviewCellClickListener {
    fun onCellClicked(carousel: CarouselWithImages)
}