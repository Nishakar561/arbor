package com.example.arbor2.Model

data class ItemDetails(
    val brand: String,
    val category_id: Int,
    val details: String,
    val discount: Int,
    val id: Int,
    val image: String,
    val price: Int,
    val sizes: List<String>,
    val stock: Int,
    val sub_category_id: Int
)