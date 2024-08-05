package project.fetchData

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import project.domain.movies.Movie
import project.domain.movies.TitleMovie
import java.net.SocketTimeoutException

class FetchMovie {

    private val authorizationEnv = System.getenv("AUTH_API_TMDB")
    private val authorization = "Bearer $authorizationEnv"
    private val url = "https://api.themoviedb.org/3/"

    private val client = OkHttpClient()

    private fun buildMovieIdRequest(url : String): Request {
        return Request.Builder()
            .url(url)
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", authorization)
            .build()
    }

    fun fetchMovieById(id: Int): Movie? {
        val request = buildMovieIdRequest("$url/movie/$id")
        return try {
            client.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val jsonString = response.body?.string() ?: return null
                    val jsonObject = JSONObject(jsonString)
                    movieByIdParser(jsonObject)
                } else {
                    null
                }
            }
        } catch (e: SocketTimeoutException) {
            // Handle timeout exception (connection or read timeout)
            println("Request timed out: ${e.message}")
            null
        }
    }

    fun fetchMoviesByTitle(title: String) : List<TitleMovie>? {
        val request = buildMovieIdRequest("$url/search/movie?query=$title")
        try{
            client.newCall(request).execute().use { response ->
                return if(response.isSuccessful) {
                    val jsonString = response.body?.string() ?: return null
                    val jsonObject = JSONObject(jsonString)
                    val jsonArray = jsonObject.getJSONArray("results")
                    val movies = mutableListOf<TitleMovie>()
                    jsonArray.forEach {
                        val movie = movieByTittleParser(it as JSONObject)
                        movies.add(movie)
                    }
                    movies
                } else null
            }
        } catch (e: SocketTimeoutException) {
            // Handle timeout exception (connection or read timeout)
            println("Request timed out: ${e.message}")
            return null
        }
    }

}