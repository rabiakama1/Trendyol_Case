package com.example.rabiakamaproject.list.model

import com.google.gson.annotations.SerializedName

class ProductSliderModel {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var name: String = ""
    @SerializedName("imageUrl")
    var imageUrl: String = ""
    @SerializedName("marketPrice")
    var marketPrice: Double = 0.0
    @SerializedName("categoryHierarchy")
    var categoryHierarchy: String = ""

}