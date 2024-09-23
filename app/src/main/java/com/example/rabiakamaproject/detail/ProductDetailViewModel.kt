package com.rabiakamaproject.detail

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rabiakamaproject.base.BaseViewModel
import com.example.rabiakamaproject.detail.model.ProductDetailItem
import com.example.rabiakamaproject.util.IntentConst

class ProductDetailViewModel: BaseViewModel() {

    private val _productDetailItem = MutableLiveData<ProductDetailItem>()
    val productDetailItem: LiveData<ProductDetailItem> by lazy {
        _productDetailItem
    }

    fun setIntentExtras(extras: Bundle?) {
        val productDetailItem = ProductDetailItem().apply {
            productImg = extras?.getString(IntentConst.PRODUCT_IMG)
            price = extras?.getString(IntentConst.PRODUCT_MARKET_PRICE)
            title = extras?.getString(IntentConst.PRODUCT_NAME)
            category = extras?.getString(IntentConst.CATEGORY_HIERARCHY)
        }
        _productDetailItem.postValue(productDetailItem)
    }
}