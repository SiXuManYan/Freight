package com.fatcloud.account.ui.task.progress

import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseActivity

/**
 * Created by Wangsw on 2020/11/16 0016 14:17.
 * </br>
 *
 */
class BookProgressActivity :BaseActivity() {

    override fun getLayoutId() = R.layout.activity_task_progress

    override fun initViews() {
        setMainTitle(getString(R.string.book_detail))
    }



}