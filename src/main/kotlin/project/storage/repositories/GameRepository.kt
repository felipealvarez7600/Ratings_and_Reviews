package project.storage.repositories

import project.domain.Game

interface GameRepository {
    fun addGame(game: Game): Int?
    fun getGameById(id: Int): Game?
}