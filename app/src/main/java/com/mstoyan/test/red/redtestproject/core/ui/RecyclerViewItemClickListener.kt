package com.mstoyan.test.red.redtestproject.core.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewItemClickListener(val recyclerView: RecyclerView): View.OnClickListener {

    override fun onClick(v: View?) {
        val position = recyclerView.getChildAdapterPosition(v!!)
        onItemClick(v, position)
    }

    abstract fun onItemClick(child: View, position: Int)
}