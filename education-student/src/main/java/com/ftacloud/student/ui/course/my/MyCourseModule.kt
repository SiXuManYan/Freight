package com.ftacloud.student.ui.course.my

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/10/29 0029 13:35.
 * </br>
 *
 */
@Module
class MyCourseModule {

    @Provides
    fun viewProvider(act: MyCourseActivity): MyCourseView {
        return act
    }


}