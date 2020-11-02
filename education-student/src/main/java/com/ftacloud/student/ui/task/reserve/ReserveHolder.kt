package com.ftacloud.student.ui.task.reserve

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.Message
import com.ftacloud.student.frames.entity.ReserveList
import com.ftacloud.student.frames.entity.Task

import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_task.*

/**
 * Created by Wangsw on 2020/7/30 0030 16:13.
 * </br>
 *
 */
class ReserveHolder(parent: ViewGroup?) : BaseItemViewHolder<ReserveList>(parent, R.layout.item_reserve), LayoutContainer {

    override val containerView: View? get() = itemView

    var showTitle = false



}