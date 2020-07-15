package com.philippschumann.wallpapercarousel.model

import androidx.room.*



@Entity(tableName = "carousel_table")
data class Carousel(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "carouselId") val carouselId: Int) {
}

