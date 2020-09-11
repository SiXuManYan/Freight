package com.sugar.library.ui.view.refresh.footer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.R
import com.sugar.library.ui.view.error.AccidentView

/**
 * Created by Wangsw on 2019/9/9 16:47.
 * </br>
 *
 */

class EmptyRetryFooter constructor(context: Context) : RecyclerArrayAdapter.ItemView {

    var mContext = context

    val accident: AccidentView? = null

    override fun onBindView(headerView: View?) = Unit

    override fun onCreateView(parent: ViewGroup?): View {
        val view = LayoutInflater.from(mContext).inflate(R.layout.view_empty_footer_retry, parent, false)
        val accident = view.findViewById<AccidentView>(R.id.accident)
        accident.showRetry()
        accident.onRetryClickListener = object : AccidentView.OnRetryClickListener {
            override fun onRetryClick() {
                accentListener?.onRetryClick()
            }
        }
        return view
    }

    var accentListener: AccentRetryClickListener? = null

    interface AccentRetryClickListener {
        fun onRetryClick()
    }

}