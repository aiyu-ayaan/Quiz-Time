package com.atech.quizapp.ui.fragment.quiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.atech.quizapp.data.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val quizRepository: QuizRepository
) : ViewModel() {

    fun getQuiz(category: Int, difficulty: String) = quizRepository.getQuiz(
        category, difficulty
    )
}