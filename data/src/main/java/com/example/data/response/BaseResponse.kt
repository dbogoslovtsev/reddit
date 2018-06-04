package com.example.data.response

import com.google.gson.annotations.SerializedName

class BaseResponse<Data> {

    @SerializedName("kind")
    val kind: String = ""

    @SerializedName("data")
    val data: Data? = null

}