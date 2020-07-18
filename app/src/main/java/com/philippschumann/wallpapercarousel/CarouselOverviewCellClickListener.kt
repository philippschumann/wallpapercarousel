package com.philippschumann.wallpapercarousel

import com.philippschumann.wallpapercarousel.model.CarouselWithImages

interface CarouselOverviewCellClickListener {
    fun onCellClicked(carousel: CarouselWithImages)
}