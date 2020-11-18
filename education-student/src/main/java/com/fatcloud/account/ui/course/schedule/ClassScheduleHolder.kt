package com.fatcloud.account.ui.course.schedule

import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.TimeUtils
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.ClassSchedule
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.util.TimeUtil
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by Wangsw on 2020/9/25 0025 17:22.
 * </br>
 *
 */
class ClassScheduleHolder(parent: ViewGroup?) : BaseItemViewHolder<ClassSchedule>(parent, R.layout.item_class_schedule), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: ClassSchedule?) {
        if (data == null) {
            return
        }





    }


}