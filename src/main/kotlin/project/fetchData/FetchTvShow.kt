package project.fetchData

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import project.domain.shows.TVShow

class FetchTvShow {
    private val authorizationEnv = System.getenv("AUTH_API_TMDB")
    private val authorization = "Bearer $authorizationEnv"
    private val url = "https://api.themoviedb.org/3/"

    private fun buildTVShowIdRequest(url : String): Request {
        return Request.Builder()
            .url(url)
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", authorization)
            .build()
    }

    fun fetchTVShowById(id: Int) : TVShow? {
        val client = OkHttpClient()
        val request = buildTVShowIdRequest("$url/tv/$id")
        val response = client.newCall(request).execute()
        return if (response.isSuccessful) {
            val jsonString = response.body?.string() ?: return null
            val jsonObject = JSONObject(jsonString)
            return tvShowByIdParser(jsonObject)
        } else null
    }
}