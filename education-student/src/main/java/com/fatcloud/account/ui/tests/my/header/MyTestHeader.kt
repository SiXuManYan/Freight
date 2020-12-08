package com.fatcloud.account.ui.tests.my.header

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fatcloud.account.R

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter


/**
 * Created by Wangsw on 2020/5/26 16:46.
 * </br>
 *
 */
open class MyTestHeader constructor(private var context: Context) : RecyclerArrayAdapter.ItemView {


    lateinit var level_tv: TextView

    private var levelString = "--"

    public fun setLevel(level: String) {
        this.levelString = level
    }

    override fun onBindView(headerView: View?) {

        if (this::level_tv.isInitialized) {
            level_tv.text = levelString
        }

    }


    override fun onCreateView(parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.view_header_my_test, parent, false)
        val level_tv = view.findViewById<TextView>(R.id.level_tv)


        return view
    }


}