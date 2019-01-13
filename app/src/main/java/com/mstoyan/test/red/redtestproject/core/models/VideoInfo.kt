package com.mstoyan.test.red.redtestproject.core.models

import com.google.gson.annotations.SerializedName
import com.mstoyan.test.red.redtestproject.core.models.Thumbs
import java.math.BigDecimal

class VideoInfo {
    @SerializedName("duration") var duration = ""
    @SerializedName("views") var viewsCount = 0
    @SerializedName("video_id") var id = 0
    @SerializedName("rating") var ratingRation = BigDecimal.ZERO!!
    @SerializedName("ratings") var ratingsCount = 0
    @SerializedName("title") var title = ""
    @SerializedName("url") var url = ""
    @SerializedName("embed_url") var embedUrl = ""
    @SerializedName("thumb") var thumbUrl = ""
    @SerializedName("default_thumb") val defaultThumb = ""
    @SerializedName("publish_date") val publishDate = ""
    @SerializedName("thumbs") var thumbs: Map<String, Thumbs> = HashMap()
}