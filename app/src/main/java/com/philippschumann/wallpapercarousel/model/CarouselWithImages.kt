package com.philippschumann.wallpapercarousel.model

import androidx.room.Embedded
import androidx.room.Relation

data class CarouselWithImages(
    @Embedded val carousel: Carousel,
    @Relation(
        parentColumn = "carouselId",
        entityColumn = "carouselId"
    ) val images: List<Image>
)