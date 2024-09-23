package com.example.rabiakamaproject.list.adapter.view_holder.product_slider

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rabiakamaproject.base.BaseRecyclerViewAdapter
import com.example.rabiakamaproject.databinding.ListRowProductSliderItemBinding
import com.example.rabiakamaproject.detail.ProductDetailActivity
import com.example.rabiakamaproject.list.model.ProductItemsModel
import com.example.rabiakamaproject.list.model.ProductSliderModel
import com.example.rabiakamaproject.util.IntentConst


class ProductSliderRecyclerAdapter(val context: Context) :
    BaseRecyclerViewAdapter<ProductSliderModel, RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = ListRowProductSliderItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBind(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(dataList[position])
    }

    override fun setNewItemList(newItemList: List<ProductSliderModel>) {
        defaultSetNewItemList(newItemList)
    }

     class ItemViewHolder(private val binding: ListRowProductSliderItemBinding,private val context: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductSliderModel) {
            binding.itemTitle.text = product.name
            binding.itemPrice.text = product.marketPrice.toString()
            Glide.with(this.itemView).load(product.imageUrl).into(binding.itemImage)
            binding.sliderRowCard.setOnClickListener{
                startDetailActivity(product)
            }
        }
         /** Starts clicked Product detail activity with intent parameters */
         fun startDetailActivity(product: ProductSliderModel) {
             val bundle = Bundle()
             bundle.putString(IntentConst.PRODUCT_IMG, product.imageUrl)
             bundle.putString(IntentConst.PRODUCT_NAME, product.name)
             bundle.putString(IntentConst.PRODUCT_MARKET_PRICE, product.marketPrice.toString())
             bundle.putString(IntentConst.CATEGORY_HIERARCHY, product.categoryHierarchy)
             val intent = Intent(context, ProductDetailActivity::class.java)
             intent.putExtras(bundle)
             context.startActivity(intent)
             /*

              intent.putExtra(IntentConst.PRODUCT_IMG, product.imageUrl)
              intent.putExtra(IntentConst.PRODUCT_NAME, product.name)
              intent.putExtra(IntentConst.PRODUCT_MARKET_PRICE, product.marketPrice.toString())
              intent.putExtra(IntentConst.CATEGORY_HIERARCHY, product.categoryHierarchy)
              context.startActivity(intent)*/
         }
    }

}