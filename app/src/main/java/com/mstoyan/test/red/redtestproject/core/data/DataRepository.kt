package com.mstoyan.test.red.redtestproject.core.data

import android.content.Context
import com.mstoyan.test.red.redtestproject.core.models.VideoInfo

class DataRepository private constructor(
    private val remoteDataSource: DataSource,
    private val localDataSource: DataSource
) {

    fun getPhotos(context: Context, page: Int, searchString: String, callback: DataSource.VideoListCallback){
        remoteDataSource.getVideosList(page, searchString, object: DataSource.VideoListCallback {
            override fun onSuccess(infos: List<VideoInfo>, page: Int) {
                callback.onSuccess(infos, page)
                //todo add saving
            }

            override fun onFailure(t: Throwable) {
                callback.onFailure(t)
            }
        })
    }

    fun getVideoEmbed(context: Context, id: Int, callback: DataSource.EmbedCodeCallback){
        remoteDataSource.getEmbedCode(id, callback)
    }

    companion object {

        private var instance: DataRepository? = null

        fun getInstance(remoteDataSource: DataSource, localDataSource: DataSource): DataRepository {
            if (null == instance){
                instance = DataRepository(remoteDataSource, localDataSource)
            }
            return instance!!
        }
    }
}