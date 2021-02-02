package com.example.arbor2.Model

data class Response(
    val address: String,
    val id: Int,
    val isSuccess: Int,
    val message: String,
    val mobile: String,
    val name: String,
    val email: String,
    val username: String
)