package com.example.rabiakamaproject.list.adapter.view_holder.product_slider

import android.content.Context
import androidx.recyclerview.widget.*
import com.example.rabiakamaproject.databinding.ListRowProductSliderBinding
import com.example.rabiakamaproject.list.model.ProductItemsModel


class ProductSliderViewHolder (private val binding: ListRowProductSliderBinding) : RecyclerView.ViewHolder(binding.root) {

    val context: Context = itemView.getContext()
    var productSliderRecyclerAdapter = ProductSliderRecyclerAdapter(context)

    fun bind(product: ProductItemsModel) {
        productSliderRecyclerAdapter = ProductSliderRecyclerAdapter(context)
        binding.itemHorizontalList.apply {
            adapter = productSliderRecyclerAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}