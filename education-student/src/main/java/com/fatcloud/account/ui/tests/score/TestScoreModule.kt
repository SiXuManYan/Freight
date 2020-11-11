package com.fatcloud.account.ui.tests.score

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/27 0027 20:09.
 * </br>
 *
 */
@Module
class TestScoreModule {


    @Provides
    fun viewProvider(activity: TestScoreActivity): TestScoreView {
        return activity
    }

}