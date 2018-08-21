package com.mydemoapp.ui.gif.grid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mydemoapp.R
import rx.Observable
import rx.subjects.PublishSubject
import android.support.v7.widget.RecyclerView.ViewHolder
import com.mydemoapp.common.utils.imageloader.GlideApp
import com.mydemoapp.data.database.repository.gif.Gif
import kotlinx.android.synthetic.main.row_gif_grid.view.*


class GifAdapter : RecyclerView.Adapter<GifAdapter.GifHolder>() {
    private val itemClicks = PublishSubject.create<Pair<Int, Int>>()
    var arrGif: ArrayList<Gif> = ArrayList()

    fun swapAdapter(arrGifs: ArrayList<Gif>) {
        this.arrGif.clear()
        this.arrGif.addAll(arrGifs)
        notifyDataSetChanged()
    }

    fun changeLikeCount(gif: Gif, adapterPosition: Int) {
        arrGif[adapterPosition] = gif
        notifyItemChanged(adapterPosition)
    }

    fun observeClicks(): Observable<Pair<Int, Int>> {
        return itemClicks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifHolder {
        return GifHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_gif_grid, parent, false), itemClicks)
    }

    override fun getItemCount(): Int {
        return if (arrGif.size > 0) {
            arrGif.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: GifAdapter.GifHolder, position: Int) {
        setDataForGifHolder(holder)
    }

    private fun setDataForGifHolder(gifHolder: GifHolder) {
        val gifDataModel = arrGif[gifHolder.adapterPosition]
        gifHolder.bind(gifDataModel)
    }

    class GifHolder(var view: View, var clickSubject: PublishSubject<Pair<Int, Int>>) : ViewHolder(view) {
        init {
            view.setOnClickListener { clickSubject.onNext(Pair(adapterPosition, 0)) }

            view.ivLike.setOnClickListener { clickSubject.onNext(Pair(adapterPosition, 1)) }

            view.ivDislike.setOnClickListener { clickSubject.onNext(Pair(adapterPosition, 2)) }
        }

        fun bind(gifDataModel: Gif) {
            GlideApp.with(view.context).asGif().load(gifDataModel.gifURL).into(itemView.ivGif)
            view.tvLikesCount.text = view.context.resources.getQuantityString(R.plurals.likes, gifDataModel.upCount, gifDataModel.upCount)
            view.tvDislikeCount.text = view.context.resources.getQuantityString(R.plurals.dislikes, gifDataModel.downCount, gifDataModel.downCount)
        }
    }
}