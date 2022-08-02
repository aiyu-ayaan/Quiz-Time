package com.atech.quizapp.ui.fragment.quiz

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.atech.quizapp.R
import com.atech.quizapp.data.retrofit.Result
import com.atech.quizapp.databinding.FragmentQuizBinding
import com.atech.quizapp.ui.main_activity.CommunicatorViewModel
import com.atech.quizapp.utils.DataState
import com.atech.quizapp.utils.category_list
import com.atech.quizapp.utils.handleCustomBackPressed
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private val binding: FragmentQuizBinding by viewBinding()
    private val viewModel: QuizViewModel by viewModels()
    private val communicatorViewModel: CommunicatorViewModel by activityViewModels()
    private val TAG = "QuizFragment"
    private var points = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            textViewQuestion.text = resources.getString(
                R.string.question_description,
                getCategory(communicatorViewModel.userDetail?.category ?: 0),
            )
            getData()
        }
        customBackPressed()
    }

    private fun customBackPressed() {
        handleCustomBackPressed {
            makeDialog()
        }
    }

    private fun makeDialog() {
        val dialog = MaterialAlertDialogBuilder(requireContext())
        dialog.setTitle("Warning")
        dialog.setMessage("Are you sure you want to leave the quiz?")
        dialog.setPositiveButton("Yes") { _, _ ->
            findNavController().popBackStack()
        }
        dialog.setNegativeButton("No") { _, _ ->
            // do nothing
        }
        dialog.show()
    }


    private fun getCategory(category: Int): String =
        category_list.find { it.id == category }?.name ?: "Any Category"

    private fun getData() = lifecycleScope.launchWhenCreated {
        viewModel.getQuiz(
            communicatorViewModel.userDetail!!.category,
            communicatorViewModel.userDetail!!.difficulty
        ).collect { dataState ->
            when (dataState) {
                is DataState.Error -> {
                    Toast.makeText(requireContext(), "${dataState.exception}", Toast.LENGTH_LONG)
                        .show()
                }
                DataState.Loading -> {
                    binding.apply {
                        progressIndicatorLoading.isVisible = true
                    }
                }
                is DataState.Success -> {
                    binding.apply {
                        pager.isVisible = true
                        progressIndicatorLoading.isVisible = false
                    }
                    val list = getFragment(dataState.data)
                    val adapter = ViewPager2Adapter(
                        list,
                        childFragmentManager,
                        lifecycle
                    )
                    binding.pager.apply {
                        this.isUserInputEnabled = false;
                        this.adapter = adapter
                    }

                }
            }
        }
    }

    private fun getFragment(data: List<Result>): List<Fragment> =
        data.mapIndexed { index, i ->
            ViewPagerQuestionFragment().also { it ->
                it.setResultAndQuestionNumber(i, index + 1)
                it.setCheckAnswerListener {
                    checkAnswer()
                }
                it.setCorrectAnswerListener {
                    incrementValue()
                }
            }
        }

    private fun incrementValue() {
        points++
    }

    private fun checkAnswer() {
        communicatorViewModel.userDetail?.points = points
        navigateToFinalScoreSection()
    }


    private fun navigateToFinalScoreSection() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
        val action = QuizFragmentDirections.actionQuizFragmentToFinalScoreFragment()
        findNavController().navigate(action)
    }
}