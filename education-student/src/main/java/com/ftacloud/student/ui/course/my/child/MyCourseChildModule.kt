package com.ftacloud.student.ui.course.my.child

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/27 0027 10:03.
 * </br>
 *
 */

@Module
class MyCourseChildModule {

    @Provides
    fun viewProvider(fragment: MyCourseChildFragment): MyCourseChildView {
        return fragment
    }

}