package com.mydemoapp.ui.gif.core

import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.mydemoapp.R
import com.mydemoapp.data.database.repository.gif.Gif
import com.mydemoapp.ui.gif.MainActivity
import com.mydemoapp.ui.gif.grid.GifAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import rx.Observable


class GifView(context: MainActivity) {
    private var view: View

    private var adapter: GifAdapter

    init {
        val parent = FrameLayout(context)
        parent.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        view = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, true)

        adapter = GifAdapter()

        view.rvGrid.adapter = adapter
        val mLayoutManager = GridLayoutManager(context, 2)
        view.rvGrid.layoutManager = mLayoutManager
    }

    fun itemClicks(): Observable<Pair<Int, Int>> {
        return adapter.observeClicks()
    }

    fun view(): View {
        return view
    }

    fun swapAdapter(arrGif: ArrayList<Gif>) {
        adapter.swapAdapter(arrGif)
    }

    fun changeCount(gif: Gif, adapterPosition: Int) {
        adapter.changeLikeCount(gif, adapterPosition)
    }
}