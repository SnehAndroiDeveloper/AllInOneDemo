package com.mydemoapp.common.utils.imageloader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class ImageLoader {
    companion object {
        fun loadImage(context: Context, imageView: ImageView, imageURL: String, cornerRadius: Int, placeHolder: Int) {
            GlideApp.with(context)
                    .asBitmap()
                    .load(imageURL)
                    .transforms(CenterCrop(), RoundedCorners(cornerRadius))
                    .placeholder(placeHolder)
                    .error(placeHolder)
                    .into(imageView)
        }
    }
}