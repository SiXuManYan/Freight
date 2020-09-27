package com.ftacloud.student.ui.course.list

import com.ftacloud.student.ui.course.detail.CourseDetailActivity
import com.ftacloud.student.ui.course.detail.CourseDetailView
import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/27 0027 10:03.
 * </br>
 *
 */

@Module
class MyCourseModule {

    @Provides
    fun viewProvider(activity: MyCourseActivity): MyCourseView {
        return activity
    }

}