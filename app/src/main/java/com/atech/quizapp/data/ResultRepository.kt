package com.atech.quizapp.data

import com.atech.quizapp.data.retrofit.QuizApi
import com.atech.quizapp.data.retrofit.Result
import com.atech.quizapp.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultRepository @Inject constructor(
    private val quizApi: QuizApi
) {

    fun getQuiz(difficulty: String): Flow<DataState<List<Result>>> = flow {
        emit(DataState.Loading)
        try {
            val result = quizApi.getData(difficulty)
            emit(DataState.Success(result.results))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}