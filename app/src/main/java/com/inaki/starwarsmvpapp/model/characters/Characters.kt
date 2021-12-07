package com.inaki.starwarsmvpapp.model.characters


import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("message")
    val message: String,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any,
    @SerializedName("results")
    val characters: List<Character>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_records")
    val totalRecords: Int
)