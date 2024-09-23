package com.example.rabiakamaproject.network

import com.example.rabiakamaproject.list.model.ListItemModel
import com.example.rabiakamaproject.list.model.ProductItemsModel
import com.example.rabiakamaproject.list.model.ProductListModel
import com.example.rabiakamaproject.list.model.ProductSliderModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiService {
    @GET("mobile-zeus-zeus-service/widget/display/personalized?widgetPageName=interview&platform=android")
    fun getWidgetList() : Call<ListItemModel>

    @GET
    fun getProductList(@Url url: String?, @Query("Build")build:Int,): Call<List<ProductListModel>>

    @GET
    fun getSliderList(@Url url: String?, @Query("Build")build:Int,): Call<List<ProductSliderModel>>
}