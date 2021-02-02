package com.example.arbor2.Model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CartItem(
    val brand: String,
    val id: Int,
    val image: String,
    val item_amount: Int,
    val item_id: Int,
    val item_size: String,
    val price: Int
) : Parcelable