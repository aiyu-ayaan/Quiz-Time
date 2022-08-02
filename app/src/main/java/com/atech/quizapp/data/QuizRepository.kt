package com.atech.quizapp.data

import com.atech.quizapp.data.retrofit.QuizApi
import com.atech.quizapp.data.retrofit.Result
import com.atech.quizapp.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepository @Inject constructor(
    private val quizApi: QuizApi
) {

    fun getQuiz(
        category: Int,
        difficulty: String
    ): Flow<DataState<List<Result>>> = flow {
        emit(DataState.Loading)
        try {
            val result = quizApi.getData(difficulty, category)
            emit(DataState.Success(result.results))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}