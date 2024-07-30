package project.services

import kotlinx.datetime.Clock
import org.springframework.stereotype.Component
import project.repositories.TransactionManager


@Component
class MovieServices (
    private val transactionManager: TransactionManager,
    private val clock: Clock
) {

    fun createMovie() {

    }


    fun getMovieById(id: Int) {

    }
}