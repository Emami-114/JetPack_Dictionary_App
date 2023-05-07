package com.example.jetpack_dictionary.core.util

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
}