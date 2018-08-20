package com.mydemoapp.ui.gif.grid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mydemoapp.R
import com.mydemoapp.data.model.GifDataModel
import rx.Observable
import rx.subjects.PublishSubject
import android.support.v7.widget.RecyclerView.ViewHolder
import com.mydemoapp.common.utils.imageloader.GlideApp
import kotlinx.android.synthetic.main.row_gif_grid.view.*


class GifAdapter : RecyclerView.Adapter<GifAdapter.GifHolder>() {
    private val itemClicks = PublishSubject.create<Int>()
    var arrGifDataModel: ArrayList<GifDataModel> = ArrayList()

    fun swapAdapter(heroes: ArrayList<GifDataModel>) {
        this.arrGifDataModel.clear()
        this.arrGifDataModel.addAll(heroes)
        notifyDataSetChanged()
    }

    fun observeClicks(): Observable<Int> {
        return itemClicks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifHolder {
        return GifHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_gif_grid, parent, false), itemClicks)
    }

    override fun getItemCount(): Int {
        if (arrGifDataModel.size > 0) {
            return arrGifDataModel.size
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(holder: GifAdapter.GifHolder, position: Int) {
        setDataForGifHolder(holder)
    }

    private fun setDataForGifHolder(gifHolder: GifHolder) {
        val gifDataModel = arrGifDataModel[gifHolder.adapterPosition]
        gifHolder.bind(gifDataModel)
    }

    class GifHolder(var view: View, clickSubject: PublishSubject<Int>) : ViewHolder(view) {
        init {
            view.setOnClickListener { clickSubject.onNext(adapterPosition) }
        }

        fun bind(gifDataModel: GifDataModel) {
            GlideApp.with(view.context).asGif().load(gifDataModel.images.previewGif.url).into(itemView.ivGif)
        }

    }
}