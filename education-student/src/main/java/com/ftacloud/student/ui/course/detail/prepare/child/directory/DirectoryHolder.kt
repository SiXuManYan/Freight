package com.ftacloud.student.ui.course.detail.prepare.child.directory

import android.view.View
import android.view.ViewGroup
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.FormalCourseDetail
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_no_class_course_directory.*

/**
 * Created by Wangsw on 2020/11/3 0003 16:34.
 * </br>
 *
 */
class DirectoryHolder  (parent: ViewGroup?) : BaseItemViewHolder<FormalCourseDetail.ScheduleOut>(parent, R.layout.item_no_class_course_directory), LayoutContainer {

    override val containerView: View? get() = itemView

    override fun setData(data: FormalCourseDetail.ScheduleOut?) {
        index_tv.text = dataPosition.toString()
    }

}