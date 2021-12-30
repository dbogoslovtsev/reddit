package com.data.di


import com.data.OauthRetrofitService
import com.data.RedditRetrofitService
import com.data.RetrofitConfig
import com.data.interceptor.HeaderInterceptor
import com.data.interceptor.OauthHeaderInterceptor
import com.data.interceptor.TokenRefreshInterceptor
import com.domain.di.qualifier.OauthQualifier
import com.domain.keystore.KeyStore
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
open class RetrofitModule {

    //Interceptors
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(keyStore: KeyStore): HeaderInterceptor {
        return HeaderInterceptor(keyStore)
    }

    @Singleton
    @Provides
    fun provideOauthHeaderInterceptor(): OauthHeaderInterceptor {
        return OauthHeaderInterceptor()
    }

    @Singleton
    @Provides
    fun provideTokenRefreshInterceptor(oauthRetrofit: OauthRetrofitService,
                                       keyStore: KeyStore
    ): TokenRefreshInterceptor {
        return TokenRefreshInterceptor(oauthRetrofit, keyStore)
    }


    // Retrofit services
    @Singleton
    @Provides
    fun provideRedditRetrofitService(retrofit: Retrofit): RedditRetrofitService {
        return retrofit.create(RedditRetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideOauthRetrofitService(@OauthQualifier retrofit: Retrofit): OauthRetrofitService {

        return retrofit.create(OauthRetrofitService::class.java)
    }


    //okHttp clients
    @Singleton
    @Provides
    fun provideClientBuilder(loggingInterceptor: HttpLoggingInterceptor,
                             headerInterceptor: HeaderInterceptor,
                             tokenRefreshInterceptor: TokenRefreshInterceptor
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)
                .addInterceptor(tokenRefreshInterceptor)
                .connectTimeout(RetrofitConfig.CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(RetrofitConfig.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(RetrofitConfig.WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
    }

    @OauthQualifier
    @Provides
    fun provideOauthClientBuilder(loggingInterceptor: HttpLoggingInterceptor,
                                  oauthHeaderInterceptor: OauthHeaderInterceptor
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(oauthHeaderInterceptor)
                .connectTimeout(RetrofitConfig.CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(RetrofitConfig.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(RetrofitConfig.WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(baseBuilder: OkHttpClient.Builder): OkHttpClient {
        return baseBuilder.build()
    }

    @OauthQualifier
    @Provides
    fun provideOauthOkHttpClient(@OauthQualifier baseBuilder: OkHttpClient.Builder): OkHttpClient {
        return baseBuilder.build()
    }


    //Factories
    @Singleton
    @Provides
    fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }


    //Retrofits
    @Singleton
    @Provides
    fun provideRetrofitBuilder(callAdapterFactory: CallAdapter.Factory,
                               converterFactory: Converter.Factory): Retrofit.Builder {
        return Retrofit.Builder()
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)

    }

    @Singleton
    @Provides
    fun redditRetrofit(retrofitBuilder: Retrofit.Builder,
                       okHttpClient: OkHttpClient): Retrofit {
        return retrofitBuilder
                .baseUrl(RetrofitConfig.BASE_URL)
                .client(okHttpClient)
                .build()
    }

    @OauthQualifier
    @Provides
    fun oauthRetrofit(retrofitBuilder: Retrofit.Builder,
                      @OauthQualifier okHttpClient: OkHttpClient): Retrofit {
        return retrofitBuilder
                .baseUrl(RetrofitConfig.TOKEN_BASE_URL)
                .client(okHttpClient)
                .build()
    }


}
