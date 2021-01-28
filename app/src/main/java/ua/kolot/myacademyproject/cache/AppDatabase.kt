package ua.kolot.myacademyproject.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class, ActorEntity::class,
    GenreEntity::class, MovieGenreCrossRef::class,  MovieActorCrossRef::class], version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
    abstract fun genreDao(): GenreDao
    abstract fun moviesWithGenresAndActorsDao(): MovieWithGenresAndActorsDao

    companion object {

        fun create(applicationContext: Context): AppDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            Contract.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
