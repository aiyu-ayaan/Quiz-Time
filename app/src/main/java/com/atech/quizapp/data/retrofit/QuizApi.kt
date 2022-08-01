package com.atech.quizapp.data.retrofit

import com.atech.quizapp.utils.category_list
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {


    @GET("api.php?amount=10&type=multiple")
    suspend fun getData(
        @Query("difficulty") difficulty: String,
        @Query("category") category: Int = category_list[0].id
    ): RetrofitResponse
}