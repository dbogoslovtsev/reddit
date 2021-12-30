package com.data

class RetrofitConfig {

    companion object {
        const val BASE_URL = "https://oauth.reddit.com"

        const val CLIENT_ID = "e_IHK9IFZCtryw"
        const val TOKEN_BASE_URL = "https://www.reddit.com"

        const val CONNECTION_TIMEOUT_SECONDS: Long = 20
        const val READ_TIMEOUT_SECONDS: Long = 60
        const val WRITE_TIMEOUT_SECONDS: Long = 60
    }

}