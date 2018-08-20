package com.mydemoapp.network

import com.mydemoapp.data.model.ResponseDataModel
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable


interface GifApi {
    @GET(WSConstant.WS_SEARCH)
    fun getData(
            @Query(WSConstant.PARAM_API_KEY) apiKey: String,
            @Query(WSConstant.PARAM_Q) q: String,
            @Query(WSConstant.PARAM_LIMIT) limit: Int,
            @Query(WSConstant.PARAM_OFFSET) offset: Int,
            @Query(WSConstant.PARAM_RATING) rating: String,
            @Query(WSConstant.PARAM_LANG) lang: String
    ): Observable<ResponseDataModel>
}