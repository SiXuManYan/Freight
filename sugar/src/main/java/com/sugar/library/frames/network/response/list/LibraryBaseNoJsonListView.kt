package com.sugar.library.frames.network.response.list

import com.sugar.library.frames.network.response.LibraryBaseView


interface LibraryBaseNoJsonListView<T> : LibraryBaseView {
    fun bindList(list: ArrayList<T>, isFirstPage: Boolean, last: Boolean)
    fun onRefresh()
    fun onLoadMore()

}