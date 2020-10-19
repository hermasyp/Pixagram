package com.catnip.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Entity(tableName = "feeds")
data class FeedLocalEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "webformatHeight")
    val webformatHeight: Int,

    @ColumnInfo(name = "imageWidth")
    val imageWidth: Int,

    @ColumnInfo(name = "favorites")
    val favorites: Int,

    @ColumnInfo(name = "previewHeight")
    val previewHeight: Int,

    @ColumnInfo(name = "webformatURL")
    val webformatURL: String,

    @ColumnInfo(name = "userImageURL")
    val userImageURL: String,

    @ColumnInfo(name = "previewURL")
    val previewURL: String,

    @ColumnInfo(name = "comments")
    val comments: Int,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "imageHeight")
    val imageHeight: Int,

    @ColumnInfo(name = "tags")
    val tags: String,

    @ColumnInfo(name = "previewWidth")
    val previewWidth: Int,

    @ColumnInfo(name = "downloads")
    val downloads: Int,

    @ColumnInfo(name = "user_id")
    val userId: Int,

    @ColumnInfo(name = "largeImageURL")
    val largeImageURL: String,

    @ColumnInfo(name = "pageURL")
    val pageURL: String,

    @ColumnInfo(name = "imageSize")
    val imageSize: Int,

    @ColumnInfo(name = "webformatWidth")
    val webformatWidth: Int,

    @ColumnInfo(name = "user")
    val user: String,

    @ColumnInfo(name = "views")
    val views: Int,

    @ColumnInfo(name = "likes")
    val likes: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)