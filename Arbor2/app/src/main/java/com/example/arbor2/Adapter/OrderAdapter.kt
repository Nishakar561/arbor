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
import kotlinx.android.synthetic.main.order_item.view.*

class OrderAdapter(
    private val context: Context,
    private val orderList: ArrayList<OrderItem>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<OrderAdapter.OrderHolder>() {
    class OrderHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val itemImage: ImageView = itemView.image
        private val brandName: TextView = itemView.brand
        private val itemSize: TextView = itemView.item_size
        private val itemAmount: TextView = itemView.item_amount
        private val orderDateAndTime: TextView = itemView.order_date_and_time
        private val deliveryDateAndTime: TextView = itemView.delivery_date_and_time
        private val price: TextView = itemView.price
        private val paymentMethod: TextView = itemView.payment_method
        private val cancelButton: Button = itemView.cancel_item

        fun initializer(holder: OrderHolder, orderList: ArrayList<OrderItem>, data: OrderItem, action: OnItemClickListener, position: Int){
            Picasso.get().load(BaseURL.BASE_URL+data.image).into(holder.itemImage)
            holder.brandName.text = data.brand
            holder.itemAmount.text = ("Item amount: ${data.item_amount}")
            holder.orderDateAndTime.text = ("Order date and time : ${data.order_date}")
            holder.deliveryDateAndTime.text = ("Expected delivery date and time: ${data.delivery_date}")
            holder.price.text = ("Price: ${data.price}")
            holder.paymentMethod.text = ("Payment method: ${data.payment_method}")

                if(data.item_size == ""){
                    holder.itemSize.text = ""
                } else{
                    holder.itemSize.text = ("Size: ${data.item_size}")
                }

                itemView.setOnClickListener {
                    action.onItemClick(data.item_id)
                }

            holder.cancelButton.setOnClickListener {
                action.onButtonClick(data.id,orderList,position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(item_id: Int)
        fun onButtonClick(id: Int,orderList: ArrayList<OrderItem>,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.order_item,parent,false)
        return OrderHolder(itemView)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        holder.initializer(holder,orderList,orderList[position],onItemClickListener, position)
    }
}