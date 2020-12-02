package com.superherosquadmaker.service.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero")
data class CacheHero(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val description: String,
    @ColumnInfo val avatarUrl: String,
    @ColumnInfo val avatarImageExtension: String
)