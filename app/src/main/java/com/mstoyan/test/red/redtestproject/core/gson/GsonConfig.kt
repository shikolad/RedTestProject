package com.mstoyan.test.red.redtestproject.core.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mstoyan.test.red.redtestproject.core.models.EmbedResponse
import com.mstoyan.test.red.redtestproject.core.models.VideoInfo

class GsonConfig {
    companion object {

        val simpleGson: Gson
        val cacheGson: Gson
        val networkGson: Gson

        init {
            simpleGson = Gson()
            cacheGson = Gson()
            val builder = GsonBuilder()
            builder.registerTypeAdapter(ThumbsParser.thumbsMapType, ThumbsParser())
            builder.registerTypeAdapter(VideoInfo::class.java, VideoInfoParser())
            builder.registerTypeAdapter(EmbedResponse::class.java, EmbedResponseParser())
            networkGson = builder.create()
        }
    }
}