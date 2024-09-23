package com.example.rabiakamaproject.list.adapter.view_holder.product_listing

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rabiakamaproject.base.SpacingItemDecorator
import com.example.rabiakamaproject.databinding.ListRowProductListingBinding

class ProductListViewHolder(private val binding: ListRowProductListingBinding, private val mContext: Context) : RecyclerView.ViewHolder(binding.root) {

    val context: Context = itemView.context
    var productListRecyclerAdapter = ProductListRecyclerAdapter(context)

    fun bind() {
        productListRecyclerAdapter = ProductListRecyclerAdapter(mContext)
        binding.itemVerticalList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(mContext, 2, RecyclerView.VERTICAL, false)
            val x = (resources.displayMetrics.density * 4).toInt() //converting dp to pixels
            addItemDecoration(SpacingItemDecorator(x)) //setting space between items in RecyclerView
            adapter = productListRecyclerAdapter
        }
    }
}