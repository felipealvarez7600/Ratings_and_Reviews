package project.fetchData

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import project.domain.shows.TVShow
import project.domain.shows.TVShowTitle
import java.net.SocketTimeoutException

class FetchTvShow {
    private val authorizationEnv = System.getenv("AUTH_API_TMDB")
    private val authorization = "Bearer $authorizationEnv"
    private val url = "https://api.themoviedb.org/3/"

    private val client = OkHttpClient()

    private fun buildTVShowIdRequest(url : String): Request {
        return Request.Builder()
            .url(url)
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", authorization)
            .build()
    }

    fun fetchTVShowById(id: Int) : TVShow? {
        val request = buildTVShowIdRequest("$url/tv/$id")
        try{
            client.newCall(request).execute().use { response ->
                return if(response.isSuccessful) {
                    val jsonString = response.body?.string() ?: return null
                    val jsonObject = JSONObject(jsonString)
                    tvShowByIdParser(jsonObject)
                } else null
            }
        } catch (e: SocketTimeoutException) {
            // Handle timeout exception (connection or read timeout)
            println("Request timed out: ${e.message}")
            return null
        }
    }

    fun fetchTVShowsByTitle(title: String) : List<TVShowTitle>? {
        val request = buildTVShowIdRequest("$url/search/tv?query=$title")
        try{
            client.newCall(request).execute().use { response ->
                return if(response.isSuccessful) {
                    val jsonString = response.body?.string() ?: return null
                    val jsonObject = JSONObject(jsonString)
                    val jsonArray = jsonObject.getJSONArray("results")
                    val tvShows = mutableListOf<TVShowTitle>()
                    jsonArray.forEach {
                        val tvShow = tvShowByTittleParser(it as JSONObject)
                        tvShows.add(tvShow)
                    }
                    tvShows
                } else null
            }
        } catch (e: SocketTimeoutException) {
            // Handle timeout exception (connection or read timeout)
            println("Request timed out: ${e.message}")
            return null
        }
    }
}