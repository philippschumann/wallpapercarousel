package com.philippschumann.wallpapercarousel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images_table")
data class Image(@PrimaryKey @ColumnInfo(name = "path") val path: String, val carouselId: Int) {
}