package project.storage.db_access

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.transaction.TransactionIsolationLevel
import project.storage.Transaction
import project.storage.TransactionManager

class JDBITransactionManager (
    private val jdbi: Jdbi
) : TransactionManager{
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
            val transaction = JDBITransaction(handle)
            block(transaction)
        }
}