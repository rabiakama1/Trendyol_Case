package com.example.rabiakamaproject.list.adapter.view_holder.single_banner

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rabiakamaproject.databinding.ListRowSingleBannerItemBinding
import com.example.rabiakamaproject.list.model.ProductItemsModel

class SingleBannerViewHolder (private val binding: ListRowSingleBannerItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(product: ProductItemsModel) {
        Glide.with(this.itemView).load(product.bannerContents[0].imageUrl).into(binding.singleBannerItem)
    }

}