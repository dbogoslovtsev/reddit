package com.example.domain.entity

import com.google.gson.annotations.SerializedName

class Post {

    @SerializedName("subreddit_name_prefixed")
    val subredditPrefixed: String = ""

    @SerializedName("title")
    val title: String = ""

    @SerializedName("author")
    val author: String = ""

    @SerializedName("created_utc")
    val created_utc: Long = 0

    @SerializedName("score")
    val score: String = ""

    @SerializedName("num_comments")
    val num_comments: String = ""

    @SerializedName("url")
    var url: String = ""

    @SerializedName("thumbnail")
    val thumbnail: String = ""

    @SerializedName("name")
    val id: String = ""


}