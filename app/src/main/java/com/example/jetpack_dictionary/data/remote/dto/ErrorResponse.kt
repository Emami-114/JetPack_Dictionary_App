package com.example.jetpack_dictionary.data.remote.dto

data class ErrorResponse(
    val message: String,
    val resolution: String,
    val title: String
)