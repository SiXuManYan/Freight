package com.ftacloud.student.ui.course.detail.experience

import com.ftacloud.student.ui.course.detail.experience.ExperienceCourseDetailActivity
import com.ftacloud.student.ui.course.detail.experience.ExperienceCourseDetailView
import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/23 0023 9:56.
 * </br>
 *
 */
@Module
class ExperienceCourseDetailModule {


    @Provides
    fun viewProvider(activity: ExperienceCourseDetailActivity): ExperienceCourseDetailView {
        return activity
    }

}