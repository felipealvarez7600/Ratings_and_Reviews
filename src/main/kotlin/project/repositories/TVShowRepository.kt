package project.repositories

import project.domain.shows.TVShow

interface TVShowRepository {
    fun addTVShow(tvShow: TVShow): Int
    fun addGenreToTVShow(genreId: Int, tvShowId: Int): Int
    fun addProductionCompanyToTVShow(companyId: Int, tvShowId: Int): Int
    fun addProductionCountryToTVShow(countryId: String, tvShowId: Int): Int
    fun addSpokenLanguageToTVShow(languageId: String, tvShowId: Int): Int
    fun addNetworkToTVShow(networkId: Int, tvShowId: Int): Int
    fun addCreatedByToTVShow(createdById: Int, tvShowId: Int): Int
    fun getTVShowById(id: Int): TVShow?
}