package com.superherosquadmaker.service.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.superherosquadmaker.service.db.model.CacheHero

@Dao
interface HeroSquadDao {
    @Query("SELECT * FROM hero")
    fun getAll(): List<CacheHero>

    @Query("SELECT * FROM hero WHERE hero.id = :id LIMIT 1")
    fun getHeroById(id: Int): CacheHero?

    @Query("SELECT * FROM hero WHERE id NOT NULL")
    fun getAllTeammate(): List<CacheHero>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(teammates: List<CacheHero>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(teammate: CacheHero)

    @Query("DELETE FROM hero WHERE hero.id = :id")
    fun delete(id: Int)
}