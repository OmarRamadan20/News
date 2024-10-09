package com.example.data.api.sourceResponse

import com.google.gson.annotations.SerializedName

data class SourcesResponse(

    @field:SerializedName("sources")
	val sourceDtos: List<SourceDto?>? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("message")
    val message:String?=null,


    @field:SerializedName("code")
    val code: String? = null
)