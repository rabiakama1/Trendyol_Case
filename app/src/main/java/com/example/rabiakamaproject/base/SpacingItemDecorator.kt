package com.example.rabiakamaproject.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecorator(private val padding: Int) : RecyclerView.ItemDecoration()
{
    override fun getItemOffsets(
        outRect: Rect, //Rect holds four integer coordinates for a rectangle
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    )
    {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = padding
        outRect.bottom = padding
        outRect.left = padding
        outRect.right = padding
    }
}