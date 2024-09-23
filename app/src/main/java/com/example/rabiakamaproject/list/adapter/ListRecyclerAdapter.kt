package com.example.rabiakamaproject.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rabiakamaproject.base.BaseRecyclerViewAdapter
import com.example.rabiakamaproject.databinding.ListRowProductListingBinding
import com.example.rabiakamaproject.databinding.ListRowProductSliderBinding
import com.example.rabiakamaproject.databinding.ListRowSingleBannerItemBinding
import com.example.rabiakamaproject.list.adapter.ListRecyclerAdapter.Const.LISTING
import com.example.rabiakamaproject.list.adapter.ListRecyclerAdapter.Const.SINGLE
import com.example.rabiakamaproject.list.adapter.ListRecyclerAdapter.Const.SLIDER
import com.example.rabiakamaproject.list.adapter.view_holder.product_listing.ProductListViewHolder
import com.example.rabiakamaproject.list.adapter.view_holder.product_slider.ProductSliderViewHolder
import com.example.rabiakamaproject.list.adapter.view_holder.single_banner.SingleBannerViewHolder
import com.example.rabiakamaproject.list.model.ProductItemsModel


class ListRecyclerAdapter(val context: Context) :
    BaseRecyclerViewAdapter<ProductItemsModel, RecyclerView.ViewHolder>() {

    private object Const {
        const val SINGLE = 0
        const val SLIDER = 1
        const val LISTING = 2
    }

    override fun onBind(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == SINGLE) {
            (holder as SingleBannerViewHolder).bind(dataList[position])
        } else if (getItemViewType(position) == SLIDER) {
            (holder as ProductSliderViewHolder).bind(dataList[position])
            holder.productSliderRecyclerAdapter.setNewItemList(dataList[position].horizontalList)
        } else if (getItemViewType(position) == LISTING) {
            (holder as ProductListViewHolder).bind()
            holder.productListRecyclerAdapter.setNewItemList(dataList[position].verticalList)
        }
    }

    override fun setNewItemList(newItemList: List<ProductItemsModel>) {
        defaultSetNewItemList(newItemList)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    /**returns datalist[position] displaytype for widgets orientation */
    override fun getItemViewType(position: Int): Int {
        return when (dataList[position].displayType) {
            "SINGLE" -> SINGLE
            "SLIDER" -> SLIDER
            else -> {
                LISTING
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SINGLE -> {
                val view =
                    ListRowSingleBannerItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                SingleBannerViewHolder(view)
            }
            SLIDER -> {
                val view =
                    ListRowProductSliderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ProductSliderViewHolder(view)

            }
            else -> {
                val view =
                    ListRowProductListingBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ProductListViewHolder(view, context)
            }

        }
    }

}