package com.example.domain.entity

import com.google.gson.annotations.SerializedName


class TopPosts {

    @SerializedName("dist")
    val count: Int = 0

    @SerializedName("children")
    val posts: ResponseData<Post>? = null

    @SerializedName("after")
    val after: String? = null

    @SerializedName("before")
    val before: String? = null
}