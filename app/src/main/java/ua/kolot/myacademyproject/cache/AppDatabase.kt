package ua.kolot.myacademyproject.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [MovieEntity::class, ActorEntity::class,
        GenreEntity::class, MovieGenreCrossRefEntity::class, MovieActorCrossRefEntity::class], version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
    abstract fun genreDao(): GenreDao
    abstract fun moviesWithGenresAndActorsDao(): MovieWithGenresAndActorsDao

    companion object {
        private const val DATABASE_NAME = "movies.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: create(context).also { INSTANCE = it }
            }

        private fun create(applicationContext: Context): AppDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
