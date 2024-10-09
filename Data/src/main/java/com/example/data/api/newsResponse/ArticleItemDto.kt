package com.example.data.api.newsResponse

import com.example.data.api.sourceResponse.SourceDto
import com.example.domain.model.ArticleItem
import com.google.gson.annotations.SerializedName

data class ArticleItemDto(

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val sourceDto: SourceDto? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("content")
	val content: String? = null
) {
	fun toArticle(): ArticleItem {
		return ArticleItem(publishedAt ,author, urlToImage, description, source = sourceDto?.toSource(),title,url,content)
	}
}