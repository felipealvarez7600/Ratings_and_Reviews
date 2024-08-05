package project.repositories

/**
 * Interface Transaction used to assist in the transactions.
 */
interface Transaction {
    //Movies repository.
    val moviesRepository: MovieRepository
    //Miscellaneous repository.
    val movieShowMiscRepository: MovieShowMisc

    //Function to use when an error happen to roll back the information to a solid state.
    fun rollback()
}