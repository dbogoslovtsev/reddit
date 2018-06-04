package com.example.data

class RetrofitConfig {

    companion object {
        val BASE_URL = "https://oauth.reddit.com"

        val CLIENT_ID = "e_IHK9IFZCtryw"
        val TOKEN_BASE_URL = "https://www.reddit.com"

        val CONNECTION_TIMEOUT_SECONDS: Long = 20
        val READ_TIMEOUT_SECONDS: Long = 60
        val WRITE_TIMEOUT_SECONDS: Long = 60
    }

}