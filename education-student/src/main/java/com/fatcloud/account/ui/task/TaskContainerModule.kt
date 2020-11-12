package com.fatcloud.account.ui.task

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/11/12 0012 15:09.
 * </br>
 *
 */
@Module
class TaskContainerModule {

    @Provides
    fun viewProvider(activity: TaskContainerActivity): TaskContainerView {
        return activity
    }


}