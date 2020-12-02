package com.superherosquadmaker.repository.di

import com.superherosquadmaker.repository.DatabaseGateway
import com.superherosquadmaker.repository.IDatabaseGateway
import com.superherosquadmaker.repository.SquadHeroRepository
import com.superherosquadmaker.repository.MarvelHeroDataSource
import com.superherosquadmaker.repository.SquadDataSource
import com.superherosquadmaker.repository.MarvelHeroRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideMarvelHeroRepo(marvelRepo: MarvelHeroRepository): MarvelHeroDataSource

    @Binds
    abstract fun provideSquadHeroRepo(squadRepo: SquadHeroRepository): SquadDataSource

    @Binds
    abstract fun provideDatabaseGateway(gateway: DatabaseGateway): IDatabaseGateway
}