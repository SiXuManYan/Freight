package com.ftacloud.freightuser.frames.components.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import butterknife.ButterKnife
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.sugar.library.util.CommonUtils


/**
 * 列表复用项目基类
 */
open class BaseItemViewHolder<T> : BaseViewHolder<T> {

    var onChildViewClickListener: OnChildViewClickListener? = null
//    var onMediaPlayListener: OnMediaPlayListener? = null

    constructor(parent: ViewGroup?, @LayoutRes res: Int) : super(parent, res)

    constructor(itemView: View) : super(itemView)

    init {
        ButterKnife.bind(this, itemView)
    }

    protected fun <T> startActivity(target: Class<T>) {
        context.startActivity(Intent(context!!.applicationContext, target))
    }

    protected fun <T> startActivity(target: Class<T>, bundle: Bundle) {
        context.startActivity(Intent(context!!.applicationContext, target).putExtras(bundle))
    }

    protected fun <T> startActivityForResultAfterLogin(target: Class<T>, bundle: Bundle, requestCode: Int) {
        if (CommonUtils.isLogin()) {
            (context as Activity).startActivityForResult(
                Intent(context!!.applicationContext, target).putExtras(bundle),
                requestCode
            )
        } else {
//            startActivity(SignUpActivity::class.java)
        }
    }

    protected fun <T> startActivityAfterLogin(target: Class<T>) {
        if (CommonUtils.isLogin()) {
            startActivity(target)
        } else {
//            startActivity(SignUpActivity::class.java)
        }
    }


    interface OnChildViewClickListener {
        fun onClick(view: View, position: Int)
    }
}