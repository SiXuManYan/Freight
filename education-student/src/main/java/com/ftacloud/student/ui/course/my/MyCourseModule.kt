package com.ftacloud.student.ui.course.my

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