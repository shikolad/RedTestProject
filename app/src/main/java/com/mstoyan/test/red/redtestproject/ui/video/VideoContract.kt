package com.mstoyan.test.red.redtestproject.ui.video

import android.content.Context
import com.mstoyan.test.red.redtestproject.core.mvp.BasePresenterInterface
import com.mstoyan.test.red.redtestproject.core.mvp.BaseViewInterface

interface VideoContract {
    interface View: BaseViewInterface {
        fun embedLoaded(embed: String)
        fun failedLoadingEmbed()
    }

    interface Presenter: BasePresenterInterface<View> {
        fun getVideoEmbed(context: Context, id: Int)
    }
}