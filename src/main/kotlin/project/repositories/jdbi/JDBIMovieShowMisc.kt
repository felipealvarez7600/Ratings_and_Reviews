package project.repositories.jdbi

import org.jdbi.v3.core.Handle
import project.domain.moviesAndShows.Genre
import project.domain.moviesAndShows.ProductionCompany
import project.domain.moviesAndShows.ProductionCountry
import project.domain.moviesAndShows.SpokenLanguage
import project.domain.shows.CreatedBy
import project.domain.shows.Network
import project.repositories.MovieShowMisc

class JDBIMovieShowMisc(private val handle: Handle) : MovieShowMisc {
    override fun addGenre(genre: Genre): Int {
        return handle.createUpdate(
            "INSERT INTO Genres (id, name) " +
                    "VALUES (:id, :name) ON CONFLICT (id) DO NOTHING"
        )
            .bind("id", genre.id)
            .bind("name", genre.name)
            .execute()
    }

    override fun addProductionCompany(company: ProductionCompany): Int {
        return handle.createUpdate(
            "INSERT INTO production_companies (id, name, logo_path, origin_country)" +
                    "VALUES (:id, :name, :logo_path, :origin_country) ON CONFLICT (id) DO NOTHING"
        )
            .bind("id", company.id)
            .bind("name", company.name)
            .bind("logo_path", company.logoPath)
            .bind("origin_country", company.originCountry)
            .execute()
    }

    override fun addProductionCountry(country: ProductionCountry): Int {
        return handle.createUpdate(
            "INSERT INTO production_countries (iso31661, name) " +
                    "VALUES (:iso31661, :name) ON CONFLICT (iso31661) DO NOTHING"
        )
            .bind("iso31661", country.iso31661)
            .bind("name", country.name)
            .execute()
    }

    override fun addSpokenLanguage(language: SpokenLanguage): Int {
        return handle.createUpdate(
            "INSERT INTO spoken_languages (english_name, iso6391, name) " +
                    "VALUES (:english_name, :iso6391, :name) ON CONFLICT (iso6391) DO NOTHING"
        )
            .bind("english_name", language.englishName)
            .bind("iso6391", language.iso6391)
            .bind("name", language.name)
            .execute()
    }

    override fun addNetwork(network: Network): Int {
        return handle.createUpdate(
            "INSERT INTO networks (id, name, logo_path, origin_country) " +
                    "VALUES (:id, :name, :logo_path, :origin_country) ON CONFLICT (id) DO NOTHING"
        )
            .bind("id", network.id)
            .bind("name", network.name)
            .bind("logo_path", network.logoPath)
            .bind("origin_country", network.originCountry)
            .execute()
    }

    override fun addCreatedBy(createdBy: CreatedBy): Int {
        return handle.createUpdate(
            "INSERT INTO created_by (id, credit_id, name) " +
                    "VALUES (:id, :credit_id, :name) ON CONFLICT (id) DO NOTHING"
        )
            .bind("id", createdBy.id)
            .bind("credit_id", createdBy.creditId)
            .bind("name", createdBy.name)
            .execute()
    }
}