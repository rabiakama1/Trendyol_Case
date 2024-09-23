package com.example.rabiakamaproject.list.model

import com.google.gson.annotations.SerializedName

class ProductListModel {
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
    @SerializedName("averageRating")
    var averageRating: Double = 0.0

}