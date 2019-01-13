package com.mstoyan.test.red.redtestproject.core.api

import com.mstoyan.test.red.redtestproject.core.models.EmbedResponse
import com.mstoyan.test.red.redtestproject.core.models.VideoInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RedtubeApiService {
    @GET("https://api.redtube.com/?data=redtube.Videos.getVideoEmbedCode&output=gson")
    fun getVideoEmbed(@Query("video_id") id: Int): Call<EmbedResponse>

    @GET("https://api.redtube.com/?output=json&data=redtube.Videos.searchVideos&ordering=newest")
    fun searchVideos(@Query("page") page: Int, @Query("search") search: String): Call<VideoInfoResponse>
}