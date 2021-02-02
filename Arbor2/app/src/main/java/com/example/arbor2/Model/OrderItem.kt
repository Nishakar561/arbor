package com.example.arbor2.Model

data class OrderItem(
    val delivery_date: String,
    val id: Int,
    val is_delivered: String,
    val item_amount: Int,
    val item_id: Int,
    val item_size: String,
    val order_date: String,
    val payment_method: String,
    val price: Int,
    val username: String,
    val image: String,
    val brand: String
)