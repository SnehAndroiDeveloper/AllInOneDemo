package com.mydemoapp.ui.gif.core

import com.mydemoapp.common.utils.NetworkUtils
import com.mydemoapp.data.model.GifDataModel
import com.mydemoapp.data.model.ResponseDataModel
import com.mydemoapp.network.GifApi
import com.mydemoapp.network.WSConstant
import com.mydemoapp.ui.gif.MainActivity
import rx.Observable

class GifModel(private val context: MainActivity, private val api: GifApi) {

    val isNetworkAvailable: Observable<Boolean>
        get() = NetworkUtils.isNetworkAvailableObservable(context)

    fun provideListGifs(text: String): Observable<ResponseDataModel> {
        val requestParam = HashMap<String, String>()
        requestParam[WSConstant.PARAM_API_KEY] = WSConstant.VAL_API_KEY
        requestParam[WSConstant.PARAM_Q] = text
        requestParam[WSConstant.PARAM_LIMIT] = WSConstant.VAL_LIMIT.toString()
        requestParam[WSConstant.PARAM_OFFSET] = WSConstant.VAL_OFFSET.toString()
        requestParam[WSConstant.PARAM_RATING] = WSConstant.VAL_RATING
        requestParam[WSConstant.PARAM_LANG] = WSConstant.VAL_LANG
        return api.getData(requestParam)
    }

    fun gotoVideoActivity(position: Int, arrGifDataModel: ArrayList<GifDataModel>) {
        context.goToVideoActivity(position, arrGifDataModel)
    }
}
