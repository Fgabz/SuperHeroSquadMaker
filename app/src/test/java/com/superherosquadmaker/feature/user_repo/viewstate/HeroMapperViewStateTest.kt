package com.superherosquadmaker.feature.user_repo.viewstate

import com.superherosquadmaker.entity.MarvelHero
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class HeroMapperViewStateTest {

    private lateinit var mapper: IHeroMapperViewState

    @Before
    fun setUp() {
        mapper = HeroMapperViewState()
    }

    @Test
    fun mapEntityToViewState() {
        val actual = mapper.mapEntityToViewState(hero, ImageSize.PICTURE_PORTRAIT_MEDIUM_STYLE)

        assertEquals(
            HeroViewState(
                1234,
                "Spiderman",
                "Peter parker",
                "https://imageUrl.io/portrait_medium.png"

            ), actual
        )
    }

    companion object {
        val hero = MarvelHero(
            1234,
            "Spiderman",
            "Peter parker",
            "http://imageUrl.io",
            "png"
        )
    }
}