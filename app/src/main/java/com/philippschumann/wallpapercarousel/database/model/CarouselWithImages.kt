package com.philippschumann.wallpapercarousel.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class CarouselWithImages(
    @Embedded val carousel: Carousel,
    @Relation(
        parentColumn = "carouselId",
        entityColumn = "carouselId",
        entity = Image::class
    ) val images: List<Image>
)