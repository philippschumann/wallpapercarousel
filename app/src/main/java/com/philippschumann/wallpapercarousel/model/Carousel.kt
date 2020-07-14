package com.philippschumann.wallpapercarousel.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carousel_table")
data class Carousel(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int)