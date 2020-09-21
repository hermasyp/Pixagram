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
data class FeedItemEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "webformatHeight")
    val webformatHeight: Int? = null,

    @ColumnInfo(name = "imageWidth")
    val imageWidth: Int? = null,

    @ColumnInfo(name = "favorites")
    val favorites: Int? = null,

    @ColumnInfo(name = "previewHeight")
    val previewHeight: Int? = null,

    @ColumnInfo(name = "webformatURL")
    val webformatURL: String? = null,

    @ColumnInfo(name = "userImageURL")
    val userImageURL: String? = null,

    @ColumnInfo(name = "previewURL")
    val previewURL: String? = null,

    @ColumnInfo(name = "comments")
    val comments: Int? = null,

    @ColumnInfo(name = "type")
    val type: String? = null,

    @ColumnInfo(name = "imageHeight")
    val imageHeight: Int? = null,

    @ColumnInfo(name = "tags")
    val tags: String? = null,

    @ColumnInfo(name = "previewWidth")
    val previewWidth: Int? = null,

    @ColumnInfo(name = "downloads")
    val downloads: Int? = null,

    @ColumnInfo(name = "user_id")
    val userId: Int? = null,

    @ColumnInfo(name = "largeImageURL")
    val largeImageURL: String? = null,

    @ColumnInfo(name = "pageURL")
    val pageURL: String? = null,

    @ColumnInfo(name = "imageSize")
    val imageSize: Int? = null,

    @ColumnInfo(name = "webformatWidth")
    val webformatWidth: Int? = null,

    @ColumnInfo(name = "user")
    val user: String? = null,

    @ColumnInfo(name = "views")
    val views: Int? = null,

    @ColumnInfo(name = "likes")
    val likes: Int? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)