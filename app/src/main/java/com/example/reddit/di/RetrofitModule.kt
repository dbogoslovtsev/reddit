package com.example.reddit.di


import com.example.data.RedditRetrofitService
import com.example.data.RetrofitConfig
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

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }


    @Singleton
    @Provides
    fun provideRadioRetrofitService(retrofit: Retrofit): RedditRetrofitService {
        return retrofit.create(RedditRetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideClientBuilder(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(RetrofitConfig.CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(RetrofitConfig.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(RetrofitConfig.WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(baseBuilder: OkHttpClient.Builder): OkHttpClient {
        return baseBuilder.build()
    }


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
                .baseUrl(RetrofitConfig.BASE_ENDPOINT)
                .client(okHttpClient)
                .build()
    }


}
