package project.storage

import org.jdbi.v3.core.transaction.TransactionIsolationLevel


/**
 * Interface with the function of running a transaction and get the result in this case using the function run to run the block of the transaction and returning the result of it.
 */
interface TransactionManager {
    /**
     * Function responsible to run a block of code and to return the result of it.
     */
    fun <R> run(isolationLevel: TransactionIsolationLevel?, block: (Transaction) -> R): R
}