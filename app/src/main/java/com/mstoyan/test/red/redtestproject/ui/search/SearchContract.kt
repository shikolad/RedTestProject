package com.mstoyan.test.red.redtestproject.ui.search

import android.content.Context
import com.mstoyan.test.red.redtestproject.core.models.VideoInfo
import com.mstoyan.test.red.redtestproject.core.mvp.BasePresenterInterface
import com.mstoyan.test.red.redtestproject.core.mvp.BaseViewInterface

interface SearchContract {

    interface View: BaseViewInterface {
        fun addVideos(videos: List<VideoInfo>, page: Int)
        fun failedLoadVideos(t: Throwable)
    }

    interface Presenter: BasePresenterInterface<View> {
        fun searchVideos(context: Context, page: Int, searchString: String)
    }
}