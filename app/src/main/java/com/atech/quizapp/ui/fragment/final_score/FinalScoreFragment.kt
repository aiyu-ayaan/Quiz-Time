package com.atech.quizapp.ui.fragment.final_score

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import com.atech.quizapp.R
import com.atech.quizapp.databinding.FragmentFinalScoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FinalScoreFragment : Fragment(R.layout.fragment_final_score) {
    private val binding: FragmentFinalScoreBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}