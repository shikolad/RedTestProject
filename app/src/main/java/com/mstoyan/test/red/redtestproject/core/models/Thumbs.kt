package com.mstoyan.test.red.redtestproject.core.models

class Thumbs(var width: Int, var height: Int) {
    var src = ArrayList<String>()
    fun addSource(source: String){
        src.add(source)
    }
}