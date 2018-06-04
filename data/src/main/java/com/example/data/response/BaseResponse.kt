package com.example.data.response

import com.google.gson.annotations.SerializedName

class BaseResponse<Data> {

    @SerializedName("kind")
    val kind: String = ""

    val data: Data? = null

}