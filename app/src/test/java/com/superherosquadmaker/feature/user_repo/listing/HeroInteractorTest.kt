package com.superherosquadmaker.feature.user_repo.listing

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.superherosquadmaker.entity.Answer
import com.superherosquadmaker.entity.FailureReason
import com.superherosquadmaker.entity.MarvelHero
import com.superherosquadmaker.repository.MarvelHeroDataSource
import com.superherosquadmaker.repository.SquadDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class HeroInteractorTest {

    private lateinit var interactor: HeroInteractor

    private val heroDataSource: MarvelHeroDataSource = mock()
    private val squadDataSource: SquadDataSource = mock()
    private val presenter: IHeroPresenter = mock()

    @Before
    fun setUp() {
        interactor = HeroInteractor(heroDataSource, squadDataSource, presenter)
    }

    @Test
    fun `Fetch list of user that succeed`() = runBlocking {
        val result = Answer.Success(
            listOf(
                MarvelHero(
                    1234,
                    "Spiderman",
                    "Peter parker",
                    "https://imageUrl.io",
                    ".png"
                )
            )
        )
        whenever(heroDataSource.fetchHeroes()).thenReturn(result)

        interactor.fetchMarvelHeroList()
        verify(presenter).displayList(result.value)
    }

    @Test
    fun `Fetch list of user that failed`() = runBlocking {
        val result = Answer.Failure(Throwable(), "Failure while fetching", FailureReason.NETWORK)
        whenever(heroDataSource.fetchHeroes()).thenReturn(result)

        interactor.fetchMarvelHeroList()
        verify(presenter).displayError()
    }

    @Test
    fun `fetch list of squad that succeed`() = runBlocking {
        val result = Answer.Success(
            listOf(
                MarvelHero(
                    1234,
                    "Spiderman",
                    "Peter parker",
                    "https://imageUrl.io",
                    ".png"
                )
            )
        )
        whenever(squadDataSource.fetchSquad()).thenReturn(result)


        interactor.fetchSquadList()
        verify(presenter).displaySquad(result.value)
    }

    @Test
    fun `Fetch list of squad that failed`() = runBlocking {
        val result = Answer.Failure(NoSuchElementException(), "Not found", FailureReason.CACHE)
        whenever(squadDataSource.fetchSquad()).thenReturn(result)

        interactor.fetchSquadList()
        verify(presenter).hideSquad()
    }
}