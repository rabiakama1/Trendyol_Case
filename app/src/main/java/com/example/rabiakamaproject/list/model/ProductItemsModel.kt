package com.example.rabiakamaproject.list.model


data class ProductItemsModel(
    var displayType: String = "",
    var bannerContents: List<BannerItemModel> = arrayListOf(),
    var horizontalList: List<ProductSliderModel> = arrayListOf(),
    var verticalList: List<ProductListModel> = arrayListOf()
)