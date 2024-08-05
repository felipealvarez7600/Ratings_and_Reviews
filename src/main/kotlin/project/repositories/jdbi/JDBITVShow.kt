package project.repositories.jdbi

import org.jdbi.v3.core.Handle

import org.jdbi.v3.core.kotlin.mapTo
import project.domain.shows.TVShow
import project.repositories.TVShowRepository

class JDBITVShow (private val handle: Handle) : TVShowRepository {
    override fun addTVShow(tvShow: TVShow): Int {
        return handle.createUpdate(
            "INSERT INTO TVShow VALUES (" +
                    ":adult, :backdrop_path, :episode_run_time, :first_air_date, :homepage, :id, :in_production, " +
                    ":languages, :last_air_date, :last_episode_to_air, :name, :next_episode_to_air, " +
                    ":number_of_episodes, :number_of_seasons, :origin_country, :original_language, " +
                    ":original_name, :overview, :popularity, :poster_path, :status, :tagline, :type, " +
                    ":vote_average, :vote_count)" +
                    "ON CONFLICT (id) DO UPDATE SET " +
                    "status = EXCLUDED.status, popularity = EXCLUDED.popularity, vote_count = EXCLUDED.vote_count, " +
                    "vote_average = EXCLUDED.vote_average"
        )
            .bind("adult", tvShow.adult)
            .bind("backdrop_path", tvShow.backdropPath)
            .bind("episode_run_time", tvShow.episodeRunTime)
            .bind("first_air_date", tvShow.firstAirDate)
            .bind("homepage", tvShow.homepage)
            .bind("id", tvShow.id)
            .bind("in_production", tvShow.inProduction)
            .bind("languages", tvShow.languages)
            .bind("last_air_date", tvShow.lastAirDate)
            .bind("last_episode_to_air", if (tvShow.lastEpisodeToAir != null) tvShow.lastEpisodeToAir.id else null)
            .bind("name", tvShow.name)
            .bind("next_episode_to_air", if (tvShow.nextEpisodeToAir != null) tvShow.nextEpisodeToAir.id else null)
            .bind("number_of_episodes", tvShow.numberOfEpisodes)
            .bind("number_of_seasons", tvShow.numberOfSeasons)
            .bind("origin_country", tvShow.originCountry)
            .bind("original_language", tvShow.originalLanguage)
            .bind("original_name", tvShow.originalName)
            .bind("overview", tvShow.overview)
            .bind("popularity", tvShow.popularity)
            .bind("poster_path", tvShow.posterPath)
            .bind("status", tvShow.status)
            .bind("tagline", tvShow.tagline)
            .bind("type", tvShow.type)
            .bind("vote_average", tvShow.voteAverage)
            .bind("vote_count", tvShow.voteCount)
            .execute()
    }

    override fun addGenreToTVShow(genreId: Int, tvShowId: Int): Int {
        return handle.createUpdate(
            "INSERT INTO TVShow_genres (genre_id, show_id) " +
                    "VALUES (:genre_Id, :show_Id) ON CONFLICT (genre_id, show_id) DO NOTHING"
        )
            .bind("genre_Id", genreId)
            .bind("show_Id", tvShowId)
            .execute()
    }

    override fun addProductionCompanyToTVShow(companyId: Int, tvShowId: Int): Int {
        return handle.createUpdate(
            "INSERT INTO TVShow_production_companies (production_company_id, show_id) " +
                    "VALUES (:production_company_id, :show_Id) ON CONFLICT (production_company_id, show_id) DO NOTHING"
        )
            .bind("production_company_id", companyId)
            .bind("show_Id", tvShowId)
            .execute()
    }

    override fun addProductionCountryToTVShow(countryId: String, tvShowId: Int): Int {
        return handle.createUpdate(
            "INSERT INTO TVShow_Production_Country (production_country_id, show_id) " +
                    "VALUES (:production_country_id, :show_Id) ON CONFLICT (production_country_id, show_id) DO NOTHING"
        )
            .bind("production_country_id", countryId)
            .bind("show_Id", tvShowId)
            .execute()
    }

    override fun addSpokenLanguageToTVShow(languageId: String, tvShowId: Int): Int {
        return handle.createUpdate(
            "INSERT INTO TVShow_spoken_languages (spoken_language_id, show_id) " +
                    "VALUES (:spoken_language_id, :show_Id) ON CONFLICT (spoken_language_id, show_id) DO NOTHING"
        )
            .bind("spoken_language_id", languageId)
            .bind("show_Id", tvShowId)
            .execute()
    }

    override fun addNetworkToTVShow(networkId: Int, tvShowId: Int): Int {
        return handle.createUpdate(
            "INSERT INTO TVShow_networks (network_id, show_id) " +
                    "VALUES (:network_id, :show_Id) ON CONFLICT (network_id, show_id) DO NOTHING"
        )
            .bind("network_id", networkId)
            .bind("show_Id", tvShowId)
            .execute()
    }

    override fun addCreatedByToTVShow(createdById: Int, tvShowId: Int): Int {
        return handle.createUpdate(
            "INSERT INTO TVShow_created_by (created_by_id, show_id) " +
                    "VALUES (:created_by_id, :show_Id) ON CONFLICT (created_by_id, show_id) DO NOTHING"
        )
            .bind("created_by_id", createdById)
            .bind("show_Id", tvShowId)
            .execute()
    }

    override fun getTVShowById(id: Int): TVShow? {
        return handle.createQuery("SELECT * FROM TVShow WHERE id = :id LIMIT 1")
            .bind("id", id)
            .mapTo<TVShow>()
            .singleOrNull()
    }
}