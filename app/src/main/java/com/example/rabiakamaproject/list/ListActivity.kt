package com.example.rabiakamaproject.list

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rabiakamaproject.R
import com.example.rabiakamaproject.base.BaseActivity
import com.example.rabiakamaproject.list.adapter.ListRecyclerAdapter
import com.example.rabiakamaproject.list.model.ProductItemsModel
import com.example.rabiakamaproject.list.view_model.ListViewModel

class ListActivity  : BaseActivity<ListViewModel>() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.content_progress) }
    private val toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    /** List recycler view adapter */
    private lateinit var listRecyclerAdapter: ListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareActionBar()
        initListRecyclerAdapter()
        observeLiveDataObjects()
    }

/**prepare activity toolbar */
    private fun prepareActionBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowTitleEnabled(true)
            title = resources.getString(R.string.app_name)
        }
    }

    /**initialize activity viewModel */
    override fun initViewModel(): ListViewModel {
        return ListViewModel(this@ListActivity)
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_list
    }

    /** Creates list recycler adapter and sets item recycler attributes */
    private fun initListRecyclerAdapter() {
        listRecyclerAdapter = ListRecyclerAdapter(this@ListActivity)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListActivity, RecyclerView.VERTICAL, false)
            adapter = listRecyclerAdapter
        }
    }

    /** Observes LiveData objects that in [ProductItemsModel] */
    private fun observeLiveDataObjects() {
        viewModel.apply {
            progress.observe(this@ListActivity, Observer { isVisible ->
                progressBar.visibility = if(isVisible) View.VISIBLE else View.GONE
            })
            products.observe(this@ListActivity, Observer { productList ->
                listRecyclerAdapter.setNewItemList(productList)
                recyclerView.apply {
                    layoutManager = LinearLayoutManager(this@ListActivity, RecyclerView.VERTICAL, false)
                    adapter = listRecyclerAdapter
                }
            })
        }
    }

}