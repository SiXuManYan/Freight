package com.ftacloud.student.ui.tests.my

import com.ftacloud.student.ui.tests.TestConditionActivity
import com.ftacloud.student.ui.tests.TestConditionView
import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/27 0027 19:17.
 * </br>
 *
 */
@Module
class MyTestModule {

    @Provides
    fun viewProvider(activity: MyTestActivity): MyTestView {
        return activity
    }

}