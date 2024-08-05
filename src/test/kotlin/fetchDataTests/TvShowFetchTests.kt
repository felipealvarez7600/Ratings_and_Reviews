package fetchDataTests

import project.fetchData.FetchTvShow
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TvShowFetchTests {

    private val fetchTvShow = FetchTvShow()

    @Test
    fun testFetchTvShowById(): Unit {
        val tittle = "I Am Not an Animal"
        val show = fetchTvShow.fetchTVShowById(100)
        assertNotNull(show)
        assertEquals(show.name, tittle)
        assertEquals(show.id, 100)
    }

    @Test
    fun testFetchTvShowByTittle(): Unit {
        val tittle = "Cars"
        val show = fetchTvShow.fetchTVShowsByTitle(tittle)
        assertNotNull(show)
        assertContains(show.map { it.name }, tittle)
        assertContains(show.map { it.id }, 2379)
    }
}