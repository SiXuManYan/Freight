package com.ftacloud.student.ui.home.holder

import android.view.View
import android.view.ViewGroup
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.home.Schedule
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by Wangsw on 2020/10/13 0013 11:17.
 * </br>
 *  订单课
 */
class OrderClassHolder (parent: ViewGroup?) : BaseItemViewHolder<Schedule>(parent, R.layout.item_home_basic_test), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: Schedule?) {

    }

}