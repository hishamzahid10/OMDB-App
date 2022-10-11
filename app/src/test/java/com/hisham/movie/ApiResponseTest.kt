package com.hisham.movie

import com.hisham.movie.data.api.ApiKeys
import com.hisham.movie.data.api.MovieApiServices
import com.hisham.movie.utils.Constants
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ApiResponseTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client).addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApiServices::class.java)


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should fetch movies response correctly`() {
        mockWebServer.enqueueResponse("movie-api-response.json", 200)

        runBlocking {
            val actual = api.getAllMovies(ApiKeys.API_KEY).Response
            val expected = Constants.KEY_RESPONSE
            assertEquals(expected, actual)
        }
    }
}
