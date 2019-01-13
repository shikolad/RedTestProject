package com.mstoyan.test.red.redtestproject.ui.search

import android.content.Context
import com.mstoyan.test.red.redtestproject.core.data.DataRepository
import com.mstoyan.test.red.redtestproject.core.data.DataSource
import com.mstoyan.test.red.redtestproject.core.models.VideoInfo
import com.mstoyan.test.red.redtestproject.core.mvp.BasePresenter
import com.mstoyan.test.red.redtestproject.ui.search.SearchContract

class SearchPresenter(val dataRepository: DataRepository): BasePresenter<SearchContract.View>(), SearchContract.Presenter {
    override fun searchVideos(context: Context, page: Int, searchString: String) {
        dataRepository.getPhotos(context, page, searchString, object: DataSource.VideoListCallback{
            override fun onSuccess(infos: List<VideoInfo>, page: Int) {
                if (null != view){
                    view!!.addVideos(infos, page)
                }
            }

            override fun onFailure(t: Throwable) {
                if (null != view){
                    view!!.failedLoadVideos(t)
                }
            }
        })
    }

}