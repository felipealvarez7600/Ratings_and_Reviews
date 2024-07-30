package project.repositories.jdbi.utils

import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.postgres.PostgresPlugin
import project.repositories.jdbi.mappers.MovieMapper

/**
 * Function "configureWithAppRequirements" responsible to have all the functions of the mappers of the server running in this case a list of them.
 * @return Jdbi represents the Jdbi with all those mappers already configuration.
 */
fun Jdbi.configureWithAppRequirements(): Jdbi {
    //Plugins we have for our application.
    installPlugin(KotlinPlugin())
    installPlugin(PostgresPlugin())

    //Registration of all mappers we have in the application.
    registerColumnMapper(MovieMapper())

    //Return of this configuration.
    return this
}