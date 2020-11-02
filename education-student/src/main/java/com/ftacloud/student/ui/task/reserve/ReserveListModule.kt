package com.ftacloud.student.ui.task.reserve

import com.ftacloud.student.ui.task.TaskActivity
import com.ftacloud.student.ui.task.TaskView
import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/11/2 0002 11:51.
 * </br>
 *
 */
@Module
class ReserveListModule {

    @Provides
    fun viewProvider(activity: ReserveListActivity): ReserveListView {
        return activity
    }

}