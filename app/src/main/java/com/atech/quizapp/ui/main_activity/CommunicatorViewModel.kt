package com.atech.quizapp.ui.main_activity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.atech.quizapp.utils.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommunicatorViewModel @Inject constructor(
    private val state: SavedStateHandle
) : ViewModel() {

    var userDetail: UserDetail? = state["UserDetail"]
        set(value) {
            field = value
            state["UserDetail"] = value
        }
}