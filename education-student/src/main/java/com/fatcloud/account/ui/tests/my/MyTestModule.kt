package com.fatcloud.account.ui.tests.my

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