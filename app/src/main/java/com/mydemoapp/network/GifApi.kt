package com.mydemoapp.network

import android.content.ContentValues
import com.mydemoapp.data.model.ResponseDataModel
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import rx.Observable


interface GifApi {
    @GET(WSConstant.WS_SEARCH)
    fun getData(
            @QueryMap values: Map<String, String>
    ): Observable<ResponseDataModel>
}