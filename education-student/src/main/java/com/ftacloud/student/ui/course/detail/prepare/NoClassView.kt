package com.ftacloud.student.ui.course.detail.prepare

import com.flyco.tablayout.listener.OnTabSelectListener
import com.ftacloud.student.frames.entity.FormalCourseDetail
import com.sugar.library.frames.network.response.BaseTaskView

/**
 * Created by Wangsw on 2020/9/29 0029 11:15.
 * </br>
 *
 */
interface NoClassView : BaseTaskView, OnTabSelectListener, androidx.viewpager.widget.ViewPager.OnPageChangeListener {
    fun bindDetail(data: FormalCourseDetail)
}