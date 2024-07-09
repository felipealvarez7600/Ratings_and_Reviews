package project.storage.repositories

import project.domain.Show

interface ShowRepository {
    fun addShow(show: Show): Int?
    fun getShowById(id: Int): Show?
}