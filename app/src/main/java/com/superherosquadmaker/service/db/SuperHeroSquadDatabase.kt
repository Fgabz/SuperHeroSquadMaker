package com.superherosquadmaker.service.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.superherosquadmaker.service.db.dao.HeroSquadDao
import com.superherosquadmaker.service.db.model.CacheHero

@Database(
    entities = [
        CacheHero::class
    ],
    version = 1
)
abstract class SuperHeroSquadDatabase : RoomDatabase() {

    abstract fun heroSquadDao(): HeroSquadDao

    companion object {
        private const val SUPER_HERO_DB_NAME = "superhero.db"

        fun generateDatabase(app: Application) =
            Room.databaseBuilder(app.applicationContext, SuperHeroSquadDatabase::class.java, SUPER_HERO_DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}