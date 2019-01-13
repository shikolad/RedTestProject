package com.mstoyan.test.red.redtestproject.core.data

import com.mstoyan.test.red.redtestproject.core.models.VideoInfo

abstract class DataSource {

    interface VideoListCallback{
        fun onSuccess(infos: List<VideoInfo>, page: Int)
        fun onFailure(t: Throwable)
    }

    interface EmbedCodeCallback{
        fun onSuccess(embedCode: String)
        fun onFailure(t: Throwable)
    }

    abstract fun getVideosList(page: Int, searchString: String, callback: VideoListCallback)
    abstract fun getEmbedCode(id: Int, callback: EmbedCodeCallback)
}