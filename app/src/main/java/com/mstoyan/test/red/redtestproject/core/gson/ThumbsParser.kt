package com.mstoyan.test.red.redtestproject.core.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.mstoyan.test.red.redtestproject.core.models.Thumbs
import java.lang.reflect.Type

class ThumbsParser: JsonDeserializer<HashMap<String, Thumbs>> {
    companion object {
        val thumbsMapType = object: TypeToken<Map<String, Thumbs>>(){}.type
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): HashMap<String, Thumbs> {
        val result = HashMap<String, Thumbs>()

        val array = json!!.asJsonArray
        for (i in 0 until array.size()){
            val item = array[i].asJsonObject
            val size = item.get("size").asString
            if (!result.containsKey(size)){
                result.put(size, Thumbs(item.get("width").asInt, item.get("height").asInt))
            }
            result[size]!!.addSource(item.get("src").asString)
        }

        return result
    }
}