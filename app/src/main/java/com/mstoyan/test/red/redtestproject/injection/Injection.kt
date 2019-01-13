package com.mstoyan.test.red.redtestproject.injection

import com.mstoyan.test.red.redtestproject.core.data.CacheDataSource
import com.mstoyan.test.red.redtestproject.core.data.DataRepository
import com.mstoyan.test.red.redtestproject.core.data.RedtubeDataSource
import com.mstoyan.test.red.redtestproject.core.gson.GsonConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Injection{
    companion object {
        fun provideDataRepository(): DataRepository {
            val httpClient = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                .client(httpClient)
                .baseUrl("https://redtube.com/")
                .addConverterFactory(GsonConverterFactory.create(GsonConfig.networkGson))
                .build()
            return DataRepository.getInstance(
                RedtubeDataSource(retrofit),
                CacheDataSource()
            )
        }
    }
}