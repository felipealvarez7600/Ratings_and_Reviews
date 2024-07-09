package project.storage.db_access

import org.jdbi.v3.core.Handle
import project.storage.Transaction
import project.storage.repositories.MovieRepository

class JDBITransaction (
    private val handle: Handle
) : Transaction {
    //Value that represent the communication with the database to the movies.
    override val movieRepository: MovieRepository = JDBIMovies(handle)
    //Function used when an error happen we will do a rollback of the transaction.
    override fun rollback() { handle.rollback() }
}