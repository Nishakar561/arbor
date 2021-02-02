package com.example.arbor2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Model.SubCategoryItem
import com.example.arbor2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items.view.*

class SubCategoryAdapter(
    private val context: Context,
    private val subCategoryList: ArrayList<SubCategoryItem>,
    private val onItemClickListener: OnItemClickListener
): RecyclerView.Adapter<SubCategoryAdapter.SubCategoryHolder>() {
    class SubCategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var subCategoryImage: ImageView = itemView.item_image
        var subCategoryName: TextView = itemView.item_text

        fun initializer(subCategoryId: Int,subCategoryName: String, action: OnItemClickListener) {
            itemView.setOnClickListener {
                action.onItemClick(subCategoryId, subCategoryName)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(subCategoryId: Int,subCategoryName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.items, parent, false)
        return SubCategoryHolder(itemView)
    }

    override fun getItemCount(): Int {
        return subCategoryList.size
    }

    override fun onBindViewHolder(holder: SubCategoryHolder, position: Int) {

        Picasso.get().load(BaseURL.BASE_URL+subCategoryList[position].image).into(holder.subCategoryImage)
        holder.subCategoryName.text = subCategoryList[position].name

        holder.initializer(subCategoryList[position].id,subCategoryList[position].name,onItemClickListener)
    }


}