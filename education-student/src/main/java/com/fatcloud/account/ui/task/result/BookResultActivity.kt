package com.fatcloud.account.ui.task.result

import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseActivity
import kotlinx.android.synthetic.main.activity_book_result.*

/**
 * Created by Wangsw on 2020/11/16 0016 14:49.
 * </br>
 *
 */
class BookResultActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_book_result

    override fun initViews() {
        count_down.setOnClickListener { finish() }
    }
}