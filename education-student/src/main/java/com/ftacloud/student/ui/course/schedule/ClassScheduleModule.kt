package com.ftacloud.student.ui.course.schedule

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/25 0025 17:21.
 * </br>
 *
 */
@Module
class ClassScheduleModule {

    @Provides
    fun viewProvider(act: ClassScheduleActivity): ClassScheduleView {
        return act
    }


}