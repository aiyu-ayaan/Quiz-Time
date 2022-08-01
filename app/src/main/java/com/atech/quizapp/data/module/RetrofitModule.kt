package com.atech.quizapp.data.module

import com.atech.quizapp.data.retrofit.QuizApi
import com.atech.quizapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Singleton
    @Provides
    fun getApi(retrofit: Retrofit): QuizApi =
        retrofit.create(QuizApi::class.java)
}