package com.example.arbor2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Model.OrderItem
import com.example.arbor2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.return_item.view.*

class ReturnAdapter(
    private val context: Context,
    private val returnList: ArrayList<OrderItem>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ReturnAdapter.ReturnHolder>() {
    class ReturnHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.image
        private val brandName: TextView = itemView.brand
        private val itemSize: TextView = itemView.item_size
        private val itemAmount: TextView = itemView.item_amount
        private val orderDateAndTime: TextView = itemView.order_date_and_time
        private val deliveryDateAndTime: TextView = itemView.delivery_date_and_time
        private val price: TextView = itemView.price
        private val paymentMethod: TextView = itemView.payment_method
        private val returnButton: Button = itemView.return_button

        fun initializer(holder: ReturnHolder, returnList: ArrayList<OrderItem>, item: OrderItem, position: Int, action: OnItemClickListener){
            Picasso.get().load(BaseURL.BASE_URL+item.image).into(holder.imageView)
            holder.brandName.text = item.brand
            holder.itemSize.text = if(item.item_size == "") ("") else ("Item Size: ${item.item_size}")
            holder.itemAmount.text = ("Item amount: ${item.item_amount}")
            holder.orderDateAndTime.text = ("Order Date and Time: ${item.order_date}")
            holder.deliveryDateAndTime.text = ("Delivery Date and Time: ${item.delivery_date}")
            holder.price.text = ("Price: ${item.price}")
            holder.paymentMethod.text = ("Payment method: ${item.payment_method}")

            itemView.setOnClickListener {
                action.onItemClick(item.item_id)
            }

            holder.returnButton.setOnClickListener {
                action.onButtonClick(item.id,returnList,position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(item_id: Int)
        fun onButtonClick(id: Int, returnList: ArrayList<OrderItem>, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReturnHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.return_item,parent,false)
        return ReturnHolder(itemView)
    }

    override fun getItemCount(): Int {
        return returnList.size
    }

    override fun onBindViewHolder(holder: ReturnHolder, position: Int) {
        holder.initializer(holder,returnList,returnList[position],position,onItemClickListener)
    }
}