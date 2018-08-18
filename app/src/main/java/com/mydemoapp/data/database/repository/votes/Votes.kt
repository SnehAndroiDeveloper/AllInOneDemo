package com.mydemoapp.data.database.repository.votes

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "votes")
data class Votes(
        @ColumnInfo(name = "up_vote")
        var upCount: Int = 0,
        @ColumnInfo(name = "down_vote")
        var downCount: Int = 0
) {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "item_id")
    var itemId: Int = 0
}