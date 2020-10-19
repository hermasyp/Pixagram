package com.catnip.core.data.source.remote.entity

import com.google.gson.annotations.SerializedName

data class FeedResponseEntity(

	@field:SerializedName("webformatHeight")
	val webformatHeight: Int,

	@field:SerializedName("imageWidth")
	val imageWidth: Int,

	@field:SerializedName("favorites")
	val favorites: Int,

	@field:SerializedName("previewHeight")
	val previewHeight: Int,

	@field:SerializedName("webformatURL")
	val webformatURL: String,

	@field:SerializedName("userImageURL")
	val userImageURL: String,

	@field:SerializedName("previewURL")
	val previewURL: String,

	@field:SerializedName("comments")
	val comments: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("imageHeight")
	val imageHeight: Int,

	@field:SerializedName("tags")
	val tags: String,

	@field:SerializedName("previewWidth")
	val previewWidth: Int,

	@field:SerializedName("downloads")
	val downloads: Int,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("largeImageURL")
	val largeImageURL: String,

	@field:SerializedName("pageURL")
	val pageURL: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("imageSize")
	val imageSize: Int,

	@field:SerializedName("webformatWidth")
	val webformatWidth: Int,

	@field:SerializedName("user")
	val user: String,

	@field:SerializedName("views")
	val views: Int,

	@field:SerializedName("likes")
	val likes: Int
)