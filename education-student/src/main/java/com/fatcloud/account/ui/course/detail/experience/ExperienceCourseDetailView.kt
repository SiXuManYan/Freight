package com.fatcloud.account.ui.course.detail.experience

import com.fatcloud.account.frames.entity.CourseDetail
import com.sugar.library.frames.network.response.BaseView

/**
 * Created by Wangsw on 2020/9/23 0023 9:47.
 * </br>
 *
 */
interface ExperienceCourseDetailView : BaseView {
    fun bindData(it: CourseDetail)

    /**
     * 预约成功
     */
    fun bookingExperienceSuccess()
}