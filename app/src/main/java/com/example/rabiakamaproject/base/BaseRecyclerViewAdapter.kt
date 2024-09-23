package com.example.rabiakamaproject.base


import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T : Any, K : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<K>() {

    //region Fields
    /** Data list property to get access from child classes */
    protected var dataList: List<T> = ArrayList()

    /** to write data to views */
    abstract fun onBind(holder: K, position: Int)

    //endregion Fields

    //region Public Method

    /** data size of given type */
    override fun getItemCount(): Int = dataList.size

    /** bind layout resource to view holder class to access views */
    override fun onBindViewHolder(holder: K, position: Int) {
        onBind(holder, position)
    }

    /** to set new list in adapter class */
    abstract fun setNewItemList(newItemList: List<T>)

    /** default new list init method */
    protected fun defaultSetNewItemList(newItemList: List<T>) {
        this.dataList = ArrayList(newItemList)
        notifyDataSetChanged()
    }


}