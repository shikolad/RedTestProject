package com.mstoyan.test.red.redtestproject.core.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class CachedCallback<ParseT, SendT>: Callback<ParseT> {
    override fun onFailure(call: Call<ParseT>, t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponse(call: Call<ParseT>, response: Response<ParseT>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    abstract fun onSuccess(data: SendT)
    abstract fun onFailure(throwable: Throwable)
}