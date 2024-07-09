package project.storage.db_access

import org.jdbi.v3.core.Handle
import project.domain.Show

import org.jdbi.v3.core.kotlin.mapTo
import project.storage.repositories.ShowRepository

class JDBIShows (private val handle : Handle): ShowRepository {
    override fun addShow(show: Show) : Int? {
        return handle.createUpdate(
            "INSERT INTO Shows (title, description, rating) VALUES (:title, :description, :rating)"
        )
            .bind("title", show.title)
            .bind("description", show.description)
            .bind("rating", show.rating)
            .executeAndReturnGeneratedKeys()
            .mapTo<Int>()
            .singleOrNull()
    }

    override fun getShowById(id: Int): Show? {
        return handle.createQuery("SELECT * FROM Shows WHERE show_id = :id")
            .bind("id", id)
            .mapTo<Show>()
            .singleOrNull()
    }
}