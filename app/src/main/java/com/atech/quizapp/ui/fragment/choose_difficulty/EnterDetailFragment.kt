package com.atech.quizapp.ui.fragment.choose_difficulty

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.atech.quizapp.R
import com.atech.quizapp.databinding.FragmentEnterDetailBinding
import com.atech.quizapp.ui.main_activity.CommunicatorViewModel
import com.atech.quizapp.utils.Difficulty
import com.atech.quizapp.utils.UserDetail
import com.atech.quizapp.utils.category_list
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EnterDetailFragment : Fragment(R.layout.fragment_enter_detail) {

    private val binding: FragmentEnterDetailBinding by viewBinding()
    private val communicatorViewModel: CommunicatorViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setUpExposerDropDownCategory()
            setUpExposerDropDownDifficulty()
            buttonClick()
        }
    }

    private fun buttonClick() = binding.buttonEnter.setOnClickListener {
        val name = binding.outlinedTextFieldName.editText?.text.toString()
        val category = binding.outlinedTextFieldCategory.editText?.text.toString()
        val difficulty = binding.outlinedTextFieldDifficulty.editText?.text.toString()
        if (name.isBlank()) {
            binding.outlinedTextFieldName.error = "Name is required"
            return@setOnClickListener
        } else binding.outlinedTextFieldName.error = null

        if (category.isBlank()) {
            binding.outlinedTextFieldCategory.error = "Category is required"
            return@setOnClickListener
        } else binding.outlinedTextFieldCategory.error = null

        if (difficulty.isBlank()) {
            binding.outlinedTextFieldDifficulty.error = "Difficulty is required"
            return@setOnClickListener
        } else
            binding.outlinedTextFieldDifficulty.error = null
        val categoryId = getCategoryId(category)
        communicatorViewModel.userDetail = UserDetail(name, categoryId, difficulty)
    }

    private fun getCategoryId(category: String): Int =
        category_list.find { it.name == category }?.id ?: 0


    private fun setUpExposerDropDownDifficulty() = binding.outlinedTextFieldDifficulty.apply {
        val items = listOf(
            Difficulty.easy.name, Difficulty.medium.name, Difficulty.hard.name
        ).toTypedArray()
        (this.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
    }

    private fun setUpExposerDropDownCategory() = binding.outlinedTextFieldCategory.apply {
        val items = category_list.map {
            it.name
        }.toTypedArray()
        (this.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
    }

}