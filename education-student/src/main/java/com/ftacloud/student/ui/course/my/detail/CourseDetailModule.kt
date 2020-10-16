package com.ftacloud.student.ui.course.my.detail

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/23 0023 9:56.
 * </br>
 *
 */
@Module
class CourseDetailModule {


    @Provides
    fun viewProvider(activity: CourseDetailActivity): CourseDetailView {
        return activity
    }

}