package com.mstoyan.test.red.redtestproject.core.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.mstoyan.test.red.redtestproject.core.models.EmbedResponse
import java.lang.reflect.Type

class EmbedResponseParser: JsonDeserializer<EmbedResponse> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): EmbedResponse {
        return EmbedResponse()
    }
}