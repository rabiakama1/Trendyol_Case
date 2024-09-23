package com.example.rabiakamaproject.detail

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.rabiakamaproject.R
import com.example.rabiakamaproject.base.BaseActivity
import com.example.rabiakamaproject.detail.model.ProductDetailItem
import com.rabiakamaproject.detail.ProductDetailViewModel

class ProductDetailActivity : BaseActivity<ProductDetailViewModel>() {

    private val image by lazy { findViewById<ImageView>(R.id.detail_item_image) }
    private val title by lazy { findViewById<TextView>(R.id.detail_item_title) }
    private val category by lazy { findViewById<TextView>(R.id.detail_item_category) }
    private val price by lazy { findViewById<TextView>(R.id.item_price) }
    private val price_Text by lazy { findViewById<TextView>(R.id.detail_item_basket_price_text) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar_detail) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        viewModel.setIntentExtras(bundle)
        prepareActionBar()
        observeLiveDataObjects()
    }

    /** Observes LiveData objects that in [ProductDetailItem] */
    fun observeLiveDataObjects() {
        viewModel.apply {
            productDetailItem.observe( this@ProductDetailActivity, Observer {
                Glide.with(this@ProductDetailActivity).load(it.productImg).into(image)
                title.text = it.title
                category.text = it.category
                price.text = it.price
                price_Text.text = resources.getString(R.string.product_basket_price)
            })
        }
    }

    /**prepare activity toolbar */
    private fun prepareActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
        }
    }


    override fun initViewModel(): ProductDetailViewModel {
        return ProductDetailViewModel()
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_product_detail
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            /** when back button on action bar clicked, pop back*/
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}