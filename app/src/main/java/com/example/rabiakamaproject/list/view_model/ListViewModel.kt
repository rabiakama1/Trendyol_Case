package com.example.rabiakamaproject.list.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rabiakamaproject.base.BaseViewModel
import com.example.rabiakamaproject.helpers.ToastHelper
import com.example.rabiakamaproject.list.model.*
import com.example.rabiakamaproject.network.ApiClient
import com.example.rabiakamaproject.network.ApiService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel(mContext: Context) : BaseViewModel() {

    lateinit var service: ApiService

    /** Toast helper class to handle Toasts */
    protected val toastHelper by lazy { ToastHelper(mContext) }

    /** List of ProductItems */
    private val _products = MutableLiveData<List<ProductItemsModel>>()
    val products: LiveData<List<ProductItemsModel>> by lazy {
        startFetchProducts()
        _products
    }

    /** ApiRequest for fetching SINGLE & SLIDER & LISTING widgets*/
    fun startFetchProducts() {
        updateProgress(true)
        service = ApiClient.getClient().create(ApiService::class.java)
        val apiCallWidgets = service.getWidgetList()
        try {
            apiCallWidgets.enqueue(object : Callback<ListItemModel> {
                override fun onResponse(
                    call: Call<ListItemModel>,
                    response: Response<ListItemModel>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        //fetch items for slider products && listing products
                        viewModelScope.launch() {
                            val widgets = response.body()!!.widgets.filter {
                                (it.displayType == "SINGLE" && it.id == 311246) || (it.displayType == "SLIDER" && it.id == 311248) || (it.displayType == "LISTING" && it.id == 311423)
                            }
                            fetchProductSlider(widgets)
                        }

                    }
                }

                override fun onFailure(call: Call<ListItemModel>, t: Throwable) {
                    updateProgress(false)
                    toastHelper.showShort(t.message.toString())

                }
            })
        } catch (e: Exception) {
            updateProgress(false)
            toastHelper.showShort(e.message.toString())
        }
    }

    /** ApiRequest for fetching SLIDER widget products*/
    private fun fetchProductSlider(widgetlist: List<WidgetsItemModel>) {
        service = ApiClient.getClient().create(ApiService::class.java)
        val slider_url = widgetlist.find { it.displayType == "SLIDER" }?.fullServiceUrl
        val list_url = widgetlist.find { it.displayType == "LISTING" }?.fullServiceUrl
        val apiCallSlider = service.getSliderList(slider_url, 500)
        val apiCallList = service.getProductList(list_url, 500)
        try {
            apiCallSlider.enqueue(object : Callback<List<ProductSliderModel>> {
                override fun onResponse(
                    call: Call<List<ProductSliderModel>>,
                    response: Response<List<ProductSliderModel>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        //fetch items for listing products
                        viewModelScope.launch() {
                            fetchProductList(widgetlist, response.body()!!, apiCallList)
                        }
                    }
                }

                override fun onFailure(call: Call<List<ProductSliderModel>>, t: Throwable) {
                    updateProgress(false)
                    toastHelper.showShort(t.message.toString())

                }
            })
        } catch (e: Exception) {
            updateProgress(false)
            toastHelper.showShort(e.message.toString())
        }
    }

    /** ApiRequest for fetching LISTING widget products*/
    private fun fetchProductList(
        widgets: List<WidgetsItemModel>,
        sliderModel: List<ProductSliderModel>,
        apiCallList: Call<List<ProductListModel>>
    ) {
        apiCallList.enqueue(object : Callback<List<ProductListModel>> {
            override fun onResponse(
                call: Call<List<ProductListModel>>,
                response: Response<List<ProductListModel>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    //sets all widgets items into adapterList
                    var adapterItem: ProductItemsModel
                    val adapterItemList = ArrayList<ProductItemsModel>()
                    widgets.forEach {
                        when (it.displayType) {
                            "SINGLE" -> {
                                adapterItem = ProductItemsModel().apply {
                                    bannerContents = it.bannerContents
                                    displayType = it.displayType
                                }
                            }
                            "SLIDER" -> {
                                adapterItem = ProductItemsModel().apply {
                                    displayType = it.displayType
                                    horizontalList = sliderModel
                                }
                            }
                            else -> {
                                adapterItem = ProductItemsModel().apply {
                                    displayType = it.displayType
                                    verticalList = response.body()!!
                                }
                            }
                        }
                        adapterItemList.add(adapterItem)
                    }
                    updateProgress(false)
                    _products.postValue(adapterItemList)
                }
            }

            override fun onFailure(call: Call<List<ProductListModel>>, t: Throwable) {
                updateProgress(false)
                toastHelper.showShort(t.message.toString())
            }
        })

    }
}
