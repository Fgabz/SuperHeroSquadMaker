package com.superherosquadmaker.service.api.model

import com.google.gson.annotations.SerializedName

data class RemoteMarvelResponse(
	@field:SerializedName("data")
	val data: Data? = null
)

data class Thumbnail(

	@field:SerializedName("path")
	val path: String,

	@field:SerializedName("extension")
	val extension: String
)

data class Data(

	@field:SerializedName("total")
	val total: Int? = null,

	@field:SerializedName("offset")
	val offset: Int? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<RemoteMarvelCharacter>? = null
)

data class RemoteMarvelCharacter(

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("modified")
	val modified: String? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("resourceURI")
	val resourceURI: String? = null
)
