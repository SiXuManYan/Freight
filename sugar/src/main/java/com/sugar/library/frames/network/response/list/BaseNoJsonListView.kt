package com.sugar.library.frames.network.response.list

import com.sugar.library.frames.network.response.BaseView


interface BaseNoJsonListView<T> : BaseView {
    fun bindList(list: ArrayList<T>, isFirstPage: Boolean, last: Boolean)
    fun onRefresh()
    fun onLoadMore()

}