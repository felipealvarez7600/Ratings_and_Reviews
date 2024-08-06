package project.repositories.jdbi

import org.jdbi.v3.core.Handle
import project.repositories.MovieRepository
import project.repositories.MovieShowMisc
import project.repositories.Transaction
import project.repositories.UserRepository

/**
 * Class responsible to assist in the transaction between the jdbi and the database.
 * @property handle is the handler responsible to assist in the communication.
 * @return Transaction represent the transaction to do.
 */
class JdbiTransaction(private val handle: Handle): Transaction {

    //Value that represent the communication with the database to the users.
    override val usersRepository: UserRepository = JDBIUser(handle)

    //Value that represent the communication with the database to the movies.
    override val moviesRepository: MovieRepository = JDBIMovie(handle)

    //Value that represent the communication with the database to the miscellaneous.
    override val movieShowMiscRepository: MovieShowMisc = JDBIMovieShowMisc(handle)

    //Function used when an error happen we will do a rollback of the transaction.
    override fun rollback() { handle.rollback() }
}