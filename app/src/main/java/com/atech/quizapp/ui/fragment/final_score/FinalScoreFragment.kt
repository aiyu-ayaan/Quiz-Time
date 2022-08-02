package com.atech.quizapp.ui.fragment.final_score

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.atech.quizapp.R
import com.atech.quizapp.databinding.FragmentFinalScoreBinding
import com.atech.quizapp.ui.main_activity.CommunicatorViewModel
import com.google.android.material.transition.MaterialSharedAxis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinalScoreFragment : Fragment(R.layout.fragment_final_score) {
    private val binding: FragmentFinalScoreBinding by viewBinding()

    private val communicatorViewModel: CommunicatorViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            textViewCongratulations.text =
                resources.getString(
                    R.string.congratulations,
                    communicatorViewModel.userDetail?.name
                )
            textViewScore.text =
                resources.getString(
                    R.string.score,
                    communicatorViewModel.userDetail?.points.toString()
                )
            val progressPercentage =
                (communicatorViewModel.userDetail!!.points.toDouble() / 10.0) * 100.0
            progressIndicatorPercentage.setProgress(progressPercentage.toInt(), true )
            buttonPlayAgain.setOnClickListener {
                navigateToDetailFragment()
            }
        }
    }

    private fun navigateToDetailFragment() {
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, /* forward= */ false)
        val action = FinalScoreFragmentDirections.actionFinalScoreFragmentToEnterDetailFragment()
        findNavController().navigate(action)
    }
}