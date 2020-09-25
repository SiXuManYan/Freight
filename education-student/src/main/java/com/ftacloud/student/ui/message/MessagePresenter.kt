package com.ftacloud.student.ui.message

import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.network.response.BasePresenter
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/25 0025 14:00.
 * </br>
 *
 */
class MessagePresenter @Inject constructor(private var view: MessageView) : BasePresenter(view) {



    override fun loadList(lifecycle: LifecycleOwner, page: Int, pageSize: Int, lastItemId: String?) {


    }



}