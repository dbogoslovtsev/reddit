package com.data.di.module

import android.content.Context
import androidx.preference.PreferenceManager
import com.data.RedditRepositoryImpl
import com.data.OauthRetrofitService
import com.data.RedditRetrofitService
import com.data.RetrofitConfig
import com.data.interceptor.HeaderInterceptor
import com.data.interceptor.OauthHeaderInterceptor
import com.data.interceptor.TokenRefreshInterceptor
import com.data.keystore.KeyStoreImpl
import com.domain.di.qualifier.OauthQualifier
import com.domain.di.scope.ApplicationScope
import com.domain.keystore.KeyStore
import com.domain.repository.RedditRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
class RepositoryModule {

    @Provides
    fun provideMainRepository(redditRetrofit: RedditRetrofitService): RedditRepository {
        return RedditRepositoryImpl(redditRetrofit)
    }
}

@Module
class KeyStoreModule {

    @Provides
    @ApplicationScope
    fun provideKeyStore(appContext: Context): KeyStore {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext)
        return KeyStoreImpl(sharedPreferences)
    }
}

@Module
class RetrofitModule {

    //Interceptors
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    fun provideHeaderInterceptor(keyStore: KeyStore): HeaderInterceptor {
        return HeaderInterceptor(keyStore)
    }

    @Provides
    fun provideOauthHeaderInterceptor(): OauthHeaderInterceptor {
        return OauthHeaderInterceptor()
    }

    @Provides
    fun provideTokenRefreshInterceptor(
        oauthRetrofit: OauthRetrofitService,
        keyStore: KeyStore
    ): TokenRefreshInterceptor {
        return TokenRefreshInterceptor(oauthRetrofit, keyStore)
    }


    // Retrofit services
    @ApplicationScope
    @Provides
    fun provideRedditRetrofitService(retrofit: Retrofit): RedditRetrofitService {
        return retrofit.create(RedditRetrofitService::class.java)
    }

    @ApplicationScope
    @Provides
    fun provideOauthRetrofitService(@OauthQualifier retrofit: Retrofit): OauthRetrofitService {
        return retrofit.create(OauthRetrofitService::class.java)
    }


    //OkHttp clients
    @Provides
    fun provideClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor,
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
    fun provideOauthClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor,
        oauthHeaderInterceptor: OauthHeaderInterceptor
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(oauthHeaderInterceptor)
            .connectTimeout(RetrofitConfig.CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(RetrofitConfig.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(RetrofitConfig.WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
    }

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
    @Provides
    fun provideConverterFactory(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }


    //Retrofits
    @Provides
    fun provideRetrofitBuilder(
        converterFactory: Converter.Factory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
    }

    @ApplicationScope
    @Provides
    fun redditRetrofit(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return retrofitBuilder
            .baseUrl(RetrofitConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @ApplicationScope
    @OauthQualifier
    @Provides
    fun oauthRetrofit(
        retrofitBuilder: Retrofit.Builder,
        @OauthQualifier okHttpClient: OkHttpClient
    ): Retrofit {
        return retrofitBuilder
            .baseUrl(RetrofitConfig.TOKEN_BASE_URL)
            .client(okHttpClient)
            .build()
    }
}