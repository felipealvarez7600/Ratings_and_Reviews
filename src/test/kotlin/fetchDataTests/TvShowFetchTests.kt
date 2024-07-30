package fetchDataTests

import project.fetchData.FetchTvShow
import kotlin.test.Test
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
}