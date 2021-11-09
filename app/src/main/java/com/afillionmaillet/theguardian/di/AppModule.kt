package com.afillionmaillet.theguardian.di

import com.afillionmaillet.theguardian.core.common.Constants
import com.afillionmaillet.theguardian.core.data.remote.TheGuardianApi
import com.afillionmaillet.theguardian.features.article.data.repository.ArticleRepositoryImpl
import com.afillionmaillet.theguardian.features.article.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideTheGuardianApi(client: OkHttpClient): TheGuardianApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(TheGuardianApi::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleRepository(api: TheGuardianApi): ArticleRepository {
        return ArticleRepositoryImpl(api)
    }
}