package com.ftacloud.student.ui.tests.my.header

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ftacloud.student.R
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

/**
 * Created by Wangsw on 2020/9/27 0027 19:18.
 * </br>
 *
 */
class MyTestHeader  constructor(private var context: Context) : RecyclerArrayAdapter.ItemView  {
    override fun onBindView(headerView: View?) {

    }

    override fun onCreateView(parent: ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.view_header_my_test, parent, false)

        return view
    }



}