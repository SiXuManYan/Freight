package com.ftacloud.student.ui.tests.question

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/27 0027 11:26.
 * </br>
 *
 */
@Module
class TestQuestionModule {

    @Provides
    fun viewProvider(activity: TestQuestionActivity): TestQuestionView {
        return activity
    }


}