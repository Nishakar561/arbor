package com.example.arbor2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Model.CartItem
import com.example.arbor2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_item.view.*

class CartAdapter(
    private val context: Context,
    private val cartItemList: ArrayList<CartItem>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CartAdapter.CartHolder>(){
    class CartHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val itemImage: ImageView = itemView.image
        private val brand: TextView = itemView.brand
        private val itemAmount: TextView = itemView.item_amount
        private val itemSize: TextView = itemView.item_size
        private val itemPrice: TextView = itemView.item_price
        private val deleteButton: ImageButton = itemView.delete_button

        fun initializer(holder: CartHolder, data: CartItem,position: Int,cartItemList: ArrayList<CartItem>,action: OnItemClickListener){
            Picasso.get().load(BaseURL.BASE_URL+data.image).into(holder.itemImage)
            holder.brand.text = data.brand
            holder.itemAmount.text = ("Item amount: ${data.item_amount}")
            holder.itemSize.text = if(data.item_size == "") {("")} else{("Item size: ${data.item_size}")}
            holder.itemPrice.text = ("Price: ${data.price}")

            itemView.setOnClickListener {
                action.onItemClick(data.item_id)
            }

            holder.deleteButton.setOnClickListener {
                action.onButtonClick(data.id,cartItemList,position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(itemId: Int)
        fun onButtonClick(id: Int,cartItemList: ArrayList<CartItem>,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false)
        return CartHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cartItemList.size
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        holder.initializer(holder,cartItemList[position],position,cartItemList, onItemClickListener)
    }

}