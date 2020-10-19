package com.catnip.core.data.source.remote.entity

import com.google.gson.annotations.SerializedName

data class PixabayResponseEntity(

	@field:SerializedName("hits")
	val hits: List<FeedResponseEntity>,

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("totalHits")
	val totalHits: Int
)