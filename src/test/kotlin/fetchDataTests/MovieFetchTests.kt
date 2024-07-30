package fetchDataTests

import project.fetchData.FetchMovie
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class MovieFetchTests {

    private val fetchMovie = FetchMovie()

    @Test
    fun testFetchMovieByTittle(){
        val tittle = "Fast and Furious"
        val movie = fetchMovie.fetchMoviesByTitle(tittle)
        assertNotNull(movie)
        val firstMovie = movie.first()
        assertNotNull(firstMovie)
        assertEquals(firstMovie.title, tittle)
        assertEquals(firstMovie.id, 169822)
    }

    @Test
    fun testFetchMovieById() {
        val id = 169822
        val movie = fetchMovie.fetchMovieById(id)
        assertNotNull(movie)
        assertEquals(movie.id, id)
        assertEquals(movie.title, "Fast and Furious")
    }
}