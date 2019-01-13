package com.mstoyan.test.red.redtestproject.core.api

import com.mstoyan.test.red.redtestproject.core.gson.GsonConfig
import okhttp3.OkHttpClient
import org.junit.Test
import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RedtubeApiTests {
    @Test
    fun apiAndParsersTest(){
        val httpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonConfig.networkGson))
            .baseUrl("https://redtube.com/")
            .build()
        val redTubeApiService = retrofit.create(RedtubeApiService::class.java)

        val searchResultResponse = redTubeApiService.searchVideos(0, "skeletons").execute()
        val searchResult = searchResultResponse.body()

        assertTrue(searchResultResponse.isSuccessful)
        assertNotNull(searchResult)
        assertNotEquals(searchResult!!.videos.size, 0)
        assertFalse(searchResult.videos[0].url.isNullOrEmpty())
        assertNotEquals(searchResult.count, 0)
        assertNotEquals(searchResult.videos[0].thumbs.size, 0)
    }
}