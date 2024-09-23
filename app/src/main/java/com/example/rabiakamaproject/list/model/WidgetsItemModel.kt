package com.example.rabiakamaproject.list.model

import com.example.rabiakamaproject.list.model.BannerItemModel
import com.google.gson.annotations.SerializedName

class WidgetsItemModel {
    @SerializedName("bannerContents")
    val bannerContents : List<BannerItemModel> = arrayListOf()
   val id: Int = 0
    @SerializedName("displayType")
    val displayType: String = ""
    @SerializedName("fullServiceUrl")
    val fullServiceUrl: String = ""
}