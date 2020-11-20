package com.fatcloud.account.ui.course.schedule.pad

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/11/19 0019 14:21.
 * </br>
 *
 */
@Module
class ClassSchedulePadModule {

    @Provides
    fun viewProvider(act: ClassSchedulePadFragment): ClassSchedulePadView {
        return act
    }

}