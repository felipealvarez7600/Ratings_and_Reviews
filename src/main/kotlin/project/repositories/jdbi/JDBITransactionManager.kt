package project.repositories.jdbi

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.transaction.TransactionIsolationLevel
import org.springframework.stereotype.Component
import project.repositories.Transaction
import project.repositories.TransactionManager

/**
 * Component that has the function to access the database and to do the transaction and return the result from that transaction.
 * @property jdbi represent the database.
 * @return TransactionManager represent the manager that will do the transaction.
 */
@Component
class JdbiTransactionManager(private val jdbi: Jdbi) : TransactionManager {
    /**
     * Function "run" responsible to run a transaction to the database and get the result.
     * @param isolationLevel represent the level of isolation.
     * @param block represent the block of code to do.
     * @return R represent the result of the transaction.
     */
    override fun <R> run(
        isolationLevel: TransactionIsolationLevel?,
        block: (Transaction) -> R
    ): R =
        jdbi.inTransaction<R, Exception> { handle ->
            //isolationLevel?.let { handle.setTransactionIsolation(it) }
            val transaction = JdbiTransaction(handle)
            block(transaction)
        }
}