package project.fetchData

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class FetchMovie {

    fun fetchMovieById(id: Int) : MovieFetch?{
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://mdblist.p.rapidapi.com/?tm=800")
            .get()
            .addHeader("x-rapidapi-key", "98e2152a88msh40a19c702adb5d2p1ff8bejsn30c03e0c35cf")
            .addHeader("x-rapidapi-host", "mdblist.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()

        return if (response.isSuccessful) {
            val body = response.body?.string() ?: return null
            val checkFor = listOf("title", "description", "released", "runtime", "score")
            val movieObj = jsonStringToMap(body, checkFor)
            if(movieObj.size != 5) return null
            MovieFetch(
                title = movieObj["title"] as String,
                description = movieObj["description"] as String,
                releaseDate = movieObj["released"] as String,
                duration = (movieObj["runtime"] as Int).toLong(),
                onlineRating = movieObj["score"] as Int
            )
        } else {
            null
        }
    }

}