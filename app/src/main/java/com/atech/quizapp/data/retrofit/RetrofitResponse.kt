package com.atech.quizapp.data.retrofit

import androidx.annotation.Keep

@Keep
data class RetrofitResponse(
    val results: List<Result>
)

@Keep
data class Result(
    val category: String,
    val type: String,
    val difficulty: String,
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
) {

}

