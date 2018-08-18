package com.mydemoapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ResponseDataModel(
        @SerializedName("data")
        @Expose
        private var data: ArrayList<GifDataModel> = ArrayList(),
        @SerializedName("pagination")
        @Expose
        private var pagination: PaginationDataModel = PaginationDataModel(),
        @SerializedName("meta")
        @Expose
        private var meta: MetaDataModel = MetaDataModel()
)

data class GifDataModel(
        @SerializedName("type")
        @Expose
        private val type: String = "",
        @SerializedName("id")
        @Expose
        private val id: String = "",
        @SerializedName("slug")
        @Expose
        private val slug: String = "",
        @SerializedName("url")
        @Expose
        private val url: String = "",
        @SerializedName("bitly_gif_url")
        @Expose
        private val bitlyGifUrl: String = "",
        @SerializedName("bitly_url")
        @Expose
        private val bitlyUrl: String = "",
        @SerializedName("embed_url")
        @Expose
        private val embedUrl: String = "",
        @SerializedName("username")
        @Expose
        private val username: String = "",
        @SerializedName("source")
        @Expose
        private val source: String = "",
        @SerializedName("rating")
        @Expose
        private val rating: String = "",
        @SerializedName("content_url")
        @Expose
        private val contentUrl: String = "",
        @SerializedName("source_tld")
        @Expose
        private val sourceTld: String = "",
        @SerializedName("source_post_url")
        @Expose
        private val sourcePostUrl: String = "",
        @SerializedName("is_sticker")
        @Expose
        private val isSticker: Int = 0,
        @SerializedName("import_datetime")
        @Expose
        private val importDatetime: String = "",
        @SerializedName("trending_datetime")
        @Expose
        private val trendingDatetime: String = "",
        @SerializedName("images")
        @Expose
        private val images: ImagesDataModel = ImagesDataModel(),
        @SerializedName("title")
        @Expose
        private val title: String = "",
        @SerializedName("_score")
        @Expose
        private val score: Double = 0.toDouble()
)

data class PaginationDataModel(
        @SerializedName("total_count")
        @Expose
        private val totalCount: Int = 0,
        @SerializedName("count")
        @Expose
        private val count: Int = 0,
        @SerializedName("offset")
        @Expose
        private val offset: Int = 0
)

data class MetaDataModel(
        @SerializedName("status")
        @Expose
        private val status: Int = 0,
        @SerializedName("msg")
        @Expose
        private val msg: String = "",
        @SerializedName("response_id")
        @Expose
        private val responseId: String = ""
)

data class ImagesDataModel(
        @SerializedName("original")
        @Expose
        private val original: OriginalDataModel = OriginalDataModel(),
        @SerializedName("original_mp4")
        @Expose
        private val originalMp4: OriginalMp4DataModel = OriginalMp4DataModel(),
        @SerializedName("preview_gif")
        @Expose
        private val previewGif: PreviewGifDataModel = PreviewGifDataModel()
)

data class OriginalMp4DataModel(
        @SerializedName("width")
        @Expose
        private val width: String = "",
        @SerializedName("height")
        @Expose
        private val height: String = "",
        @SerializedName("mp4")
        @Expose
        private val mp4: String = "",
        @SerializedName("mp4_size")
        @Expose
        private val mp4Size: String = ""
)

data class PreviewGifDataModel(
        @SerializedName("url")
        @Expose
        private val url: String = "",
        @SerializedName("width")
        @Expose
        private val width: String = "",
        @SerializedName("height")
        @Expose
        private val height: String = "",
        @SerializedName("size")
        @Expose
        private val size: String = ""
)

data class OriginalDataModel(
        @SerializedName("url")
        @Expose
        private val url: String = "",
        @SerializedName("width")
        @Expose
        private val width: String = "",
        @SerializedName("height")
        @Expose
        private val height: String = "",
        @SerializedName("size")
        @Expose
        private val size: String = "",
        @SerializedName("frames")
        @Expose
        private val frames: String = "",
        @SerializedName("mp4")
        @Expose
        private val mp4: String = "",
        @SerializedName("mp4_size")
        @Expose
        private val mp4size: String = "",
        @SerializedName("webp")
        @Expose
        private val webp: String = "",
        @SerializedName("webp_size")
        @Expose
        private val webpSize: String = "",
        @SerializedName("hash")
        @Expose
        private val hash: String = ""
)