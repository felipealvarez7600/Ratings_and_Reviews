package project.repositories

/**
 * Interface Transaction used to assist in the transactions.
 */
interface Transaction {
    //Users repository.
    val moviesRepository: MovieRepository

    //Function to use when an error happen to roll back the information to a solid state.
    fun rollback()
}