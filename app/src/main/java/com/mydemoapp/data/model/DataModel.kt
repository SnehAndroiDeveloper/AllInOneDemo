package com.mydemoapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ResponseDataModel(
        @SerializedName("data")
        @Expose
        var data: ArrayList<GifDataModel> = ArrayList(),
        @SerializedName("pagination")
        @Expose
        var pagination: PaginationDataModel = PaginationDataModel(),
        @SerializedName("meta")
        @Expose
        var meta: MetaDataModel = MetaDataModel()
)

data class GifDataModel(
        @SerializedName("id")
        @Expose
        val id: String = "",
        @SerializedName("url")
        @Expose
        val url: String = "",
        @SerializedName("bitly_gif_url")
        @Expose
        val bitlyGifUrl: String = "",
        @SerializedName("bitly_url")
        @Expose
        val bitlyUrl: String = "",
        @SerializedName("embed_url")
        @Expose
        val embedUrl: String = "",
        @SerializedName("username")
        @Expose
        val username: String = "",
        @SerializedName("source")
        @Expose
        val source: String = "",
        @SerializedName("rating")
        @Expose
        val rating: String = "",
        @SerializedName("content_url")
        @Expose
        val contentUrl: String = "",
        @SerializedName("source_tld")
        @Expose
        val sourceTld: String = "",
        @SerializedName("source_post_url")
        @Expose
        val sourcePostUrl: String = "",
        @SerializedName("import_datetime")
        @Expose
        val importDatetime: String = "",
        @SerializedName("trending_datetime")
        @Expose
        val trendingDatetime: String = "",
        @SerializedName("images")
        @Expose
        val images: ImagesDataModel = ImagesDataModel(),
        @SerializedName("title")
        @Expose
        val title: String = "",
        @SerializedName("_score")
        @Expose
        val score: Double = 0.toDouble()
)

data class PaginationDataModel(
        @SerializedName("total_count")
        @Expose
        val totalCount: Int = 0,
        @SerializedName("count")
        @Expose
        val count: Int = 0,
        @SerializedName("offset")
        @Expose
        val offset: Int = 0
)

data class MetaDataModel(
        @SerializedName("status")
        @Expose
        val status: Int = 0,
        @SerializedName("msg")
        @Expose
        val msg: String = "",
        @SerializedName("response_id")
        @Expose
        val responseId: String = ""
)

data class ImagesDataModel(
        @SerializedName("original")
        @Expose
        val original: OriginalDataModel = OriginalDataModel(),
        @SerializedName("original_mp4")
        @Expose
        val originalMp4: OriginalMp4DataModel = OriginalMp4DataModel(),
        @SerializedName("preview_gif")
        @Expose
        val previewGif: PreviewGifDataModel = PreviewGifDataModel()
)

data class OriginalMp4DataModel(
        @SerializedName("width")
        @Expose
        val width: String = "",
        @SerializedName("height")
        @Expose
        val height: String = "",
        @SerializedName("mp4")
        @Expose
        val mp4: String = "",
        @SerializedName("mp4_size")
        @Expose
        val mp4Size: String = ""
)

data class PreviewGifDataModel(
        @SerializedName("url")
        @Expose
        val url: String = "",
        @SerializedName("width")
        @Expose
        val width: String = "",
        @SerializedName("height")
        @Expose
        val height: String = "",
        @SerializedName("size")
        @Expose
        val size: String = ""
)

data class OriginalDataModel(
        @SerializedName("url")
        @Expose
        val url: String = "",
        @SerializedName("width")
        @Expose
        val width: String = "",
        @SerializedName("height")
        @Expose
        val height: String = "",
        @SerializedName("size")
        @Expose
        val size: String = "",
        @SerializedName("frames")
        @Expose
        val frames: String = "",
        @SerializedName("mp4")
        @Expose
        val mp4: String = "",
        @SerializedName("mp4_size")
        @Expose
        val mp4size: String = "",
        @SerializedName("webp")
        @Expose
        val webp: String = "",
        @SerializedName("webp_size")
        @Expose
        val webpSize: String = "",
        @SerializedName("hash")
        @Expose
        val hash: String = ""
)