package com.philippschumann.wallpapercarousel.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "carousel")
class Carousel() {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "carouselId")
    var carouselId: Int = 0

    @Ignore
    val frequencyKeys: Array<String> = arrayOf("day", "hour", "minute")
    var frequency: String = frequencyKeys[1]
    var interval: Int = 3

    @Ignore
    var images: List<Image>? = null


    fun isEmpty(): Boolean {
        var empty: Boolean = true
        if (!images.isNullOrEmpty()) {
            empty = false
        }
        return empty
    }


}

