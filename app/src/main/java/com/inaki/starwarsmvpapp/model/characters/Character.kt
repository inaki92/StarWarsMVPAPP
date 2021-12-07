package com.inaki.starwarsmvpapp.model.characters


import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("name")
    val name: String,
    @SerializedName("uid")
    val uid: String,
    @SerializedName("url")
    val url: String
)