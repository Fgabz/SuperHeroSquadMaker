package com.superherosquadmaker.service.di

import android.app.Application
import com.superherosquadmaker.annotation.CacheHeroMap
import com.superherosquadmaker.annotation.CacheSquadMap
import com.superherosquadmaker.annotation.PerApp
import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.repository.service.IDatabaseService
import com.superherosquadmaker.repository.service.cache.ICacheHeroService
import com.superherosquadmaker.repository.service.cache.ICacheSquadService
import com.superherosquadmaker.service.cache.CacheHeroService
import com.superherosquadmaker.service.cache.CacheSquadService
import com.superherosquadmaker.service.cache.HeroCache
import com.superherosquadmaker.service.cache.ICacheHero
import com.superherosquadmaker.service.cache.ICacheSquad
import com.superherosquadmaker.service.cache.SquadCache
import com.superherosquadmaker.service.db.DatabaseService
import com.superherosquadmaker.service.db.SuperHeroSquadDatabase
import com.superherosquadmaker.service.db.SuperHeroSquadDatabase.Companion.generateDatabase
import com.superherosquadmaker.service.db.dao.HeroSquadDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

@Module(includes = [PersistenceModule.AbstractionModule::class])
class PersistenceModule {

    @Module
    internal abstract class AbstractionModule {
        @Binds
        abstract fun provideHeroCache(teamCache: HeroCache): ICacheHero

        @Binds
        abstract fun provideSquadCache(teamCache: SquadCache): ICacheSquad

        @Binds
        abstract fun provideHeroCacheHeroService(service: CacheHeroService): ICacheHeroService

        @Binds
        abstract fun provideHeroCacheSquadService(service: CacheSquadService): ICacheSquadService

        @Binds
        abstract fun providesDatabaseService(databaseService: DatabaseService): IDatabaseService
    }

    @PerApp
    @Provides
    fun providesAppDatabase(app: Application): SuperHeroSquadDatabase {
        return generateDatabase(app)
    }

    @PerApp
    @Provides
    fun providesSquadDao(appDatabase: SuperHeroSquadDatabase): HeroSquadDao {
        return appDatabase.heroSquadDao()
    }

    @PerApp
    @Provides
    @CacheSquadMap
    fun providesSquadCache(): ConcurrentMap<Int, MarvelHero?> {
        return ConcurrentHashMap()
    }

    @PerApp
    @Provides
    @CacheHeroMap
    fun providesHeroCache(): ConcurrentMap<Int, MarvelHero?> {
        return ConcurrentHashMap()
    }
}