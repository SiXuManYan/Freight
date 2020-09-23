package com.ftacloud.student.ui.course.detail

import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity

/**
 * Created by Wangsw on 2020/9/23 0023 9:57.
 * </br>
 *  课程详情
 */
class CourseDetailActivity : BaseMVPActivity<CourseDetailPresenter>(), CourseDetailView {



    override fun getLayoutId() = R.layout.activity_course_detail

    override fun initViews() {

    }
}