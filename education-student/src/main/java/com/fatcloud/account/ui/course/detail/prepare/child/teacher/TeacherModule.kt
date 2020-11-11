package com.fatcloud.account.ui.course.detail.prepare.child.teacher

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/11/3 0003 15:12.
 * </br>
 *
 */
@Module
class TeacherModule {


    @Provides
    fun viewProvider(fragment: TeacherFragment): TeacherView {
        return fragment
    }
}