package com.ftacloud.student.ui.course.detail.prepare

import com.ftacloud.student.ui.course.detail.prepare.NoClassActivity
import com.ftacloud.student.ui.course.detail.prepare.NoClassView
import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/29 0029 11:15.
 * </br>
 *
 */
@Module
class NoClassModule {

    @Provides
    fun viewProvider(activity: NoClassActivity): NoClassView {
        return activity
    }
}