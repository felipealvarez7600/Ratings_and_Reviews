package project.repositories

import project.domain.moviesAndShows.Genre
import project.domain.moviesAndShows.ProductionCompany
import project.domain.moviesAndShows.ProductionCountry
import project.domain.moviesAndShows.SpokenLanguage
import project.domain.shows.CreatedBy
import project.domain.shows.Network

interface MovieShowMisc {
    fun addGenre(genre: Genre): Int
    fun addProductionCompany(company: ProductionCompany): Int
    fun addProductionCountry(country: ProductionCountry): Int
    fun addSpokenLanguage(language: SpokenLanguage): Int
    fun addNetwork(network: Network): Int
    fun addCreatedBy(createdBy: CreatedBy): Int
}