package com.fatcloud.account.ui.tests

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/22 0022 15:56.
 * </br>
 *
 */
@Module
class TestConditionModule {

    @Provides
    fun viewProvider(activity: TestConditionActivity): TestConditionView {
        return activity
    }

}