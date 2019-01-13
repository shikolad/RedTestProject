package com.mstoyan.test.red.redtestproject.core.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.mstoyan.test.red.redtestproject.core.models.Thumbs
import com.mstoyan.test.red.redtestproject.core.models.VideoInfo
import java.lang.reflect.Type

class VideoInfoParser: JsonDeserializer<VideoInfo> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): VideoInfo {
        val tmpObject = json!!.asJsonObject.get("video").asJsonObject
        val thumbs = GsonConfig.networkGson.fromJson<Map<String, Thumbs>>(tmpObject.remove("thumbs"), ThumbsParser.thumbsMapType)
        val result = GsonConfig.simpleGson.fromJson(tmpObject, VideoInfo::class.java)
        result.thumbs = thumbs
        return result
    }

}