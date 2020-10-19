package com.ftacloud.student.ui.order.list.child

import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.network.response.BasePresenter
import javax.inject.Inject

class MyVoucherListPresenter @Inject constructor(private var view: MyVoucherListView) : BasePresenter(view) {


    override fun loadList(lifecycle: LifecycleOwner, page: Int, pageSize: Int, lastItemId: String?) {


    }

}