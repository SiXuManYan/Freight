package com.ftacloud.student.ui.task

import android.view.View
import android.view.ViewGroup
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.Message
import com.ftacloud.student.frames.entity.Task

import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by Wangsw on 2020/7/30 0030 16:13.
 * </br>
 *
 */
class TaskHolder(parent: ViewGroup?) : BaseItemViewHolder<Task>(parent, R.layout.item_home_task), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: Task?) {
        if (data == null) {
            return
        }


    }

}