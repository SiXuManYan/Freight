package com.ftacloud.student.ui.task

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.Message
import com.ftacloud.student.frames.entity.Task

import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_task.*

/**
 * Created by Wangsw on 2020/7/30 0030 16:13.
 * </br>
 *
 */
class TaskHolder(parent: ViewGroup?) : BaseItemViewHolder<Task>(parent, R.layout.item_home_task), LayoutContainer {

    override val containerView: View? get() = itemView

    var showTitle = false


    override fun setData(data: Task?) {

        if (data == null) {
            return
        }

        if (showTitle) {
            task_title_tv?.visibility = View.VISIBLE
        } else {
            task_title_tv?.visibility = View.GONE
        }
        Glide.with(context).load(data.productIconImg).into(tag_iv)


        title_tv.text = if (data.productName.isNotBlank()) {
            data.productName
        } else {
            data.courseName
        }
        content_tv.text = data.studyDatetime


    }

}