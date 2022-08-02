package com.atech.quizapp.ui.fragment.quiz

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.atech.quizapp.R
import com.atech.quizapp.data.retrofit.Result
import com.atech.quizapp.databinding.FragmentQuestionQuizBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerQuestionFragment() : Fragment(R.layout.fragment_question_quiz) {
    private val TAG = "FragmentQuestion"

    private var result: Result? = null
    private var questionNumber: Int? = null
    private val binding: FragmentQuestionQuizBinding by viewBinding()

    private var answer = ""
    private var checkAnswerListener: ((Int) -> Unit)? = null
    private var correctListener: (() -> Unit)? = null
    private var point: Int = 0

    fun setCheckAnswerListener(listener: (Int) -> Unit) {
        this.checkAnswerListener = listener
    }

    fun setCorrectAnswerListener(listener: () -> Unit) {
        this.correctListener = listener
    }

    fun setResultAndQuestionNumber(result: Result, questionNumber: Int) {
        this.result = result
        this.questionNumber = questionNumber
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result?.let {
            bind(result = it)
        }

    }

    private fun bind(result: Result) {
        binding.apply {
            Log.d(TAG, "onViewCreated: ${result.correct_answer}")
            textViewQuestion.text = binding.root.context
                .resources.getString(
                    R.string.sno_question,
                    questionNumber.toString(),
                    Html.fromHtml(result.question, Html.FROM_HTML_MODE_LEGACY)
                )

            val question = mutableListOf<String>()
            question.addAll(result.incorrect_answers)
            question.add(result.correct_answer)
            question.shuffle()
            radioButtonAnswer1.text = Html.fromHtml(question[0], Html.FROM_HTML_MODE_LEGACY)
            radioButtonAnswer2.text = Html.fromHtml(question[1], Html.FROM_HTML_MODE_LEGACY)
            radioButtonAnswer3.text = Html.fromHtml(question[2], Html.FROM_HTML_MODE_LEGACY)
            radioButtonAnswer4.text = Html.fromHtml(question[3], Html.FROM_HTML_MODE_LEGACY)

            setAnswer()

            val viewPager = activity?.findViewById<ViewPager2>(R.id.pager)
            buttonNext.text = if (viewPager?.currentItem == 9) "Finish" else "Next"
            binding.buttonNext.setOnClickListener {
                checkAnswer()
                if (viewPager?.currentItem == 9) {
                    checkAnswerListener?.invoke(point)
                } else {
                    viewPager?.currentItem = viewPager?.currentItem?.plus(1)!!
                }
            }
        }

    }

    private fun checkAnswer() {
        if (answer == result!!.correct_answer) {
            correctListener?.invoke()
        }
    }

    private fun FragmentQuestionQuizBinding.setAnswer() {
        radioButtonAnswer1.setOnClickListener {
            answer = radioButtonAnswer1.text.toString()
        }

        radioButtonAnswer2.setOnClickListener {
            answer = radioButtonAnswer2.text.toString()
        }
        radioButtonAnswer3.setOnClickListener {
            answer = radioButtonAnswer3.text.toString()
        }
        radioButtonAnswer4.setOnClickListener {
            answer = radioButtonAnswer4.text.toString()
        }
    }
}