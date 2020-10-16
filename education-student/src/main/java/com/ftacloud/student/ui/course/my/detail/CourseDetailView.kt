package com.ftacloud.student.ui.course.my.detail

import com.ftacloud.student.frames.entity.CourseDetail
import com.sugar.library.frames.network.response.BaseView

/**
 * Created by Wangsw on 2020/9/23 0023 9:47.
 * </br>
 *
 */
interface CourseDetailView : BaseView {
    fun bindData(it: CourseDetail)
}