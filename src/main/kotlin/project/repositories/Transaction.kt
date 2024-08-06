package project.repositories

/**
 * Interface Transaction used to assist in the transactions.
 */
interface Transaction {

    //Users repository.
    val usersRepository: UserRepository

    //Movies repository.
    val moviesRepository: MovieRepository
    //Miscellaneous repository.
    val movieShowMiscRepository: MovieShowMisc

    //Function to use when an error happen to roll back the information to a solid state.
    fun rollback()
}