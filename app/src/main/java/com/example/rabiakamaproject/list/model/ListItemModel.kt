package com.example.rabiakamaproject.list.model

import com.google.gson.annotations.SerializedName

data class ListItemModel(
    @SerializedName("widgets")
    var widgets : List<WidgetsItemModel> = arrayListOf()
)