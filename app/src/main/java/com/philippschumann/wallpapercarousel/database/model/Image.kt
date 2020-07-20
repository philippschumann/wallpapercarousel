package com.philippschumann.wallpapercarousel.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "image"/*, foreignKeys = [ForeignKey(
        entity = Carousel::class,
        parentColumns = ["carouselId"],
        childColumns = ["carouselId"],
        onDelete = CASCADE
    )]*/
)
data class Image(
    @PrimaryKey val path: String,
    @ColumnInfo(name = "carouselId") val carouselId: Int? = 0
)