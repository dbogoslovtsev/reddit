package com.domain.entity

import com.google.gson.annotations.SerializedName

class DataWrapper<Type> {

    @SerializedName("kind")
    val kind: String = ""

    @SerializedName("data")
    val data: Type? = null

}