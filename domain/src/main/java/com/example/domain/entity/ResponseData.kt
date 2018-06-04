package com.example.domain.entity

import com.google.gson.annotations.SerializedName

class ResponseData<Type> {

    @SerializedName("kind")
    val kind: String = ""

    @SerializedName("data")
    val data: List<Type> = emptyList()

}