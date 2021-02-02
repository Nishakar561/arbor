package com.example.arbor2.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Model.CategoryItem
import com.example.arbor2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items.view.*

class CategoryAdapter(
    private val context: Context,
    private val categoryList: ArrayList<CategoryItem>,
    private val onItemClickListener: OnItemClickLister
) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    class CategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var categoryImage: ImageView = itemView.item_image
        var categoryName: TextView = itemView.item_text

        fun initializer(categoryName: String,categoryId: Int, action: OnItemClickLister) {
            itemView.setOnClickListener {
                action.onItemClick(categoryId,categoryName)
            }
        }

    }

    interface OnItemClickLister {
        fun onItemClick( categoryId: Int,categoryName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.items, parent, false)
        return CategoryHolder(itemView)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        Picasso.get().load(BaseURL.BASE_URL+categoryList[position].image).into(holder.categoryImage)
        holder.categoryName.text = categoryList[position].name

        holder.initializer(categoryList[position].name,categoryList[position].id, onItemClickListener)
    }
}