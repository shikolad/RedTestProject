package com.mstoyan.test.red.redtestproject.ui.video

import android.content.Context
import com.mstoyan.test.red.redtestproject.core.data.DataRepository
import com.mstoyan.test.red.redtestproject.core.data.DataSource
import com.mstoyan.test.red.redtestproject.core.mvp.BasePresenter
import com.mstoyan.test.red.redtestproject.ui.video.VideoContract

class VideoPresenter(val dataRepository: DataRepository): BasePresenter<VideoContract.View>(), VideoContract.Presenter {
    override fun getVideoEmbed(context: Context, id: Int) {
        dataRepository.getVideoEmbed(context, id, object: DataSource.EmbedCodeCallback{
            override fun onSuccess(embedCode: String) {
                if (null != view)
                    view!!.embedLoaded(embedCode)
            }

            override fun onFailure(t: Throwable) {
                if (null != view)
                    view!!.failedLoadingEmbed()
            }
        })
    }

}