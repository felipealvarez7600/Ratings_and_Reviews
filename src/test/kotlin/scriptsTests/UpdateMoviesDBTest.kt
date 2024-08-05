package scriptsTests

import project.scripts.MovieUpdateScript
import kotlin.test.Test

class UpdateMoviesDBTest {

    private val moviesUpdate = MovieUpdateScript()

    @Test
    fun testUpdateDB() {
        val nRowsFail = moviesUpdate.updateMovies()
        println(nRowsFail)
    }
}