package com.catnip.core.domain.viewparam

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

@Parcelize
data class FeedViewParam(
    val webformatHeight: Int,
    val imageWidth: Int,
    val favorites: Int,
    val previewHeight: Int,
    val webformatURL: String,
    val userImageURL: String,
    val previewURL: String,
    val comments: Int,
    val type: String,
    val imageHeight: Int,
    val tags: String,
    val previewWidth: Int,
    val downloads: Int,
    val userId: Int,
    val largeImageURL: String,
    val pageURL: String,
    val id: Int,
    val imageSize: Int,
    val webformatWidth: Int,
    val user: String,
    val views: Int,
    val likes: Int,
    var isFavorite: Boolean = false
) : Parcelable