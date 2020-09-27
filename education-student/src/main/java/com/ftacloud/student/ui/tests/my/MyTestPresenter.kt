package com.ftacloud.student.ui.tests.my

import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.network.response.BasePresenter
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/27 0027 19:17.
 * </br>
 *
 */
class MyTestPresenter @Inject constructor(private var view: MyTestView) : BasePresenter(view) {


    override fun loadList(lifecycle: LifecycleOwner, page: Int, pageSize: Int, lastItemId: String?) {
        super.loadList(lifecycle, page, pageSize, lastItemId)
    }
}