package com.mydemoapp.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mydemoapp.R
import com.mydemoapp.data.model.GifDataModel

class GifAdapter(
        private val context: Context,
        private val arrGifDataModel: ArrayList<GifDataModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifHolder {
        return GifHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_gif_grid, parent, false))
    }

    override fun getItemCount(): Int {
        return arrGifDataModel.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        setDataForGifHolder(holder as GifHolder)
    }

    private fun setDataForGifHolder(gifHolder: GifHolder) {
        val gifDataModel = arrGifDataModel[gifHolder.adapterPosition]

        with(gifHolder.itemView) {
            //            tvItemName.text = departmentListModel.departmentName
        }
    }

    class GifHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}