package com.mstoyan.test.red.redtestproject.core.models

class EmbedResponse {

    var embedString:String = ""
    var errCode: Int = 0
    var message: String = ""

    fun error():Boolean{
        return errCode != 0
    }

    fun messageString(): String{
        return message
    }
}