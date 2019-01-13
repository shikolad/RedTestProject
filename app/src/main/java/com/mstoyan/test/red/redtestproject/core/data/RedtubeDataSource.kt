package com.mstoyan.test.red.redtestproject.core.data

import com.mstoyan.test.red.redtestproject.core.api.RedtubeApiService
import com.mstoyan.test.red.redtestproject.core.models.EmbedResponse
import com.mstoyan.test.red.redtestproject.core.models.VideoInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RedtubeDataSource(retrofitInstance: Retrofit) : DataSource() {
    private val apiService: RedtubeApiService = retrofitInstance.create(RedtubeApiService::class.java)

    override fun getVideosList(page: Int, searchString: String, callback: VideoListCallback) {
        apiService.searchVideos(page, searchString).enqueue(object: Callback<VideoInfoResponse>{
            override fun onFailure(call: Call<VideoInfoResponse>, t: Throwable) {
                callback.onFailure(t)
            }

            override fun onResponse(call: Call<VideoInfoResponse>, response: Response<VideoInfoResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()
                    if (null == result)
                        onFailure(call, IllegalStateException("Empty answer!"))
                    else
                        callback.onSuccess(result.videos, page)
                } else {
                    onFailure(call, IllegalStateException("Error receiving data: code ${response.code()}"))
                }
            }

        })
    }

    override fun getEmbedCode(id: Int, callback: EmbedCodeCallback) {
        apiService.getVideoEmbed(id).enqueue(object: Callback<EmbedResponse>{
            override fun onFailure(call: Call<EmbedResponse>, t: Throwable) {
                callback.onFailure(t)
            }

            override fun onResponse(call: Call<EmbedResponse>, response: Response<EmbedResponse>) {
                if (response.isSuccessful){
                    val result = response.body()
                    if (null == result)
                        onFailure(call, java.lang.IllegalStateException("Empty answer!"))
                    else
                        callback.onSuccess(result.embedString)
                } else {
                    onFailure(call, java.lang.IllegalStateException("Error receiving data: code ${response.code()}"))
                }
            }
        })
    }
}