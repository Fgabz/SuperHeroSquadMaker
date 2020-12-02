package com.superherosquadmaker.service.mapper.di

import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.service.db.model.CacheHero
import com.superherosquadmaker.service.mapper.HeroMapper
import com.superherosquadmaker.service.mapper.IDbMapper
import com.superherosquadmaker.service.mapper.IRemoteMarvelCharacterMapper
import dagger.Binds
import dagger.Module

@Module
abstract class MapperModule {

    @Binds
    abstract fun provideHeroMapperModel(mapper: HeroMapper): IRemoteMarvelCharacterMapper

    @Binds
    abstract fun provideCacheMapper(mapper: HeroMapper): IDbMapper<CacheHero, MarvelHero>
}