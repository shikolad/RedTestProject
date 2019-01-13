package com.mstoyan.test.red.redtestproject.core.models

import com.google.gson.annotations.SerializedName
import com.mstoyan.test.red.redtestproject.core.models.VideoInfo

class VideoInfoResponse {
    @SerializedName("videos") var videos: List<VideoInfo> = ArrayList()
    @SerializedName("count") var count: Int = 0
}