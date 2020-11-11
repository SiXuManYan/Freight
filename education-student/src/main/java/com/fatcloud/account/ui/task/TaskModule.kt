package com.fatcloud.account.ui.task

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/27 0027 14:23.
 * </br>
 *
 */
@Module
class TaskModule {

    @Provides
    fun viewProvider(fragment: TaskFragment): TaskView {
        return fragment
    }


}