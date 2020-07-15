package com.philippschumann.wallpapercarousel.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.philippschumann.wallpapercarousel.dao.CarouselDao
import com.philippschumann.wallpapercarousel.model.Carousel
import com.philippschumann.wallpapercarousel.model.Image
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Carousel::class, Image::class], version = 2)
abstract class CarouselDatabase : RoomDatabase() {

    abstract fun carouselDao(): CarouselDao

    companion object {
        @Volatile
        private var INSTANCE: CarouselDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CarouselDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarouselDatabase::class.java,
                    "carousel_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(CarouselDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class CarouselDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.carouselDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(carouselDao: CarouselDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.

            /* Not necessary as user populates db by creating Carousels
            carouselDao.deleteAll()
            var carousel=
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)*/
            carouselDao.deleteAll()
            for (i in 0 until 10) {
                var carousel: Carousel = Carousel(0)
                carouselDao.insert(carousel)
            }
        }
    }

}
