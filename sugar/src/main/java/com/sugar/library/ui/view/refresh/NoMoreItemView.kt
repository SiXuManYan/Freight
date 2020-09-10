package com.sugar.library.ui.view.refresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.R

class NoMoreItemView : RecyclerArrayAdapter.ItemView {

    override fun onBindView(headerView: View?) {
    }

    override fun onCreateView(parent: ViewGroup?): View {
        return LayoutInflater.from(parent!!.context).inflate(R.layout.view_load_no_more, parent,false)
    }

}