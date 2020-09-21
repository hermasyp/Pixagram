package com.catnip.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PixabayResponse(

	@field:SerializedName("hits")
	val hits: List<FeedItemResponse?>? = null,

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("totalHits")
	val totalHits: Int? = null
)