package com.sugar.library.ui.widget.dialog

import android.app.Dialog
import android.content.Context
import butterknife.ButterKnife
import butterknife.Unbinder
import com.sugar.library.R

/**
 * 对话框基类
 */
abstract class BaseDialog @JvmOverloads constructor(context: Context, style: Int = R.style.sugar_library_dialog, animal: Boolean = false) : Dialog(context, style) {

    private var unbinder: Unbinder? = null

    init {
        if (animal) {
            window!!.setWindowAnimations(R.style.sugar_library_dialog_fade_animation)
        }
        initView()
    }

    override fun onDetachedFromWindow() {
        unbinder = null
        super.onDetachedFromWindow()
    }

    /**
     * 初始化控件
     */
    private fun initView() {
        val view = layoutInflater.inflate(getContentId(), null)
        setContentView(view)
        unbinder = ButterKnife.bind(this, view)
    }

    abstract fun getContentId(): Int
}
