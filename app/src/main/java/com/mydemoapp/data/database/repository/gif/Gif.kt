package com.mydemoapp.data.database.repository.gif

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import android.support.annotation.NonNull
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "gif")
data class Gif(
        @ColumnInfo(name = "gif_url")
        var gifURL: String = "",
        @ColumnInfo(name = "video_url")
        var videoURL: String = "",
        @ColumnInfo(name = "up_vote")
        var upCount: Int = 0,
        @ColumnInfo(name = "down_vote")
        var downCount: Int = 0
) : Parcelable {
    @IgnoredOnParcel
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "item_id")
    var itemId: String = ""
}