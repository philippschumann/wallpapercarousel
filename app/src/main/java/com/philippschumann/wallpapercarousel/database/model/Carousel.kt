package com.philippschumann.wallpapercarousel.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "carousel")
class Carousel(var interval: Int) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "carouselId")
    var carouselId: Int = 0

    @Ignore
    var images: List<Image>? = null


}

