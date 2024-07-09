package project.storage.db_access

import org.jdbi.v3.core.Handle
import org.jdbi.v3.core.kotlin.mapTo
import project.storage.repositories.GameRepository
import project.domain.Game

class JDBIGames (private val handle: Handle) : GameRepository {
    override fun addGame(game: Game) : Int? {
        return handle.createUpdate(
            "INSERT INTO Games (title, description, release_date, rating) VALUES (:title, :description, :releaseDate, :rating)"
        )
            .bind("title", game.title)
            .bind("description", game.description)
            .bind("releaseDate", game.releaseDate.epochSecond)
            .bind("rating", game.rating)
            .executeAndReturnGeneratedKeys()
            .mapTo<Int>()
            .singleOrNull()
    }

    override fun getGameById(id: Int): Game? {
        return handle.createQuery("SELECT * FROM Games WHERE game_id = :id")
            .bind("id", id)
            .mapTo<Game>()
            .singleOrNull()
    }

}