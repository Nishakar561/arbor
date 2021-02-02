package com.example.arbor2.Adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Model.DataItem
import com.example.arbor2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.data_items.view.*
import kotlin.math.roundToInt

class DataAdapter(
    private val context: Context,
    private val dataList: ArrayList<DataItem>,
    private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<DataAdapter.DataHolder>() {
    class DataHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val brandName: TextView = itemView.brand_name
        private val stock: TextView = itemView.stock
        private val realPrice: TextView = itemView.real_price
        private val price: TextView = itemView.price
        private val discount: TextView = itemView.discount
        private val image : ImageView = itemView.data_image

        fun initializer(holder: DataHolder, data: DataItem, action : OnItemClickListener){
            Picasso.get().load(BaseURL.BASE_URL+data.image).into(image)
            holder.brandName.text = data.brand

            if(data.stock == 0){
                holder.stock.text = ("Out of stock")
            } else {
                holder.stock.text = ("${data.stock} : In Stock")
            }

            if(data.discount == 0){
                holder.realPrice.text = ("${data.price}")
                holder.price.text = ""
                discount.text = ""
            } else{
                holder.realPrice.text = ("${data.price}")
                holder.realPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

                val discountPrice = (data.price - ((data.price * data.discount) / 100.0)).roundToInt()
                holder.price.text = ("$discountPrice")
                holder.discount.text = ("${data.discount}%")
            }

            itemView.setOnClickListener {
                action.onItemClick(data.id)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.data_items,parent,false)
        return DataHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.initializer(holder, dataList[position],onItemClickListener)
    }

}