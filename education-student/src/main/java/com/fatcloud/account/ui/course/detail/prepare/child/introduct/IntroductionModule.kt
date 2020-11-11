package com.fatcloud.account.ui.course.detail.prepare.child.introduct

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/11/3 0003 15:08.
 * </br>
 *
 */
@Module
class IntroductionModule {

    @Provides
    fun viewProvider(fragment: IntroductionFragment): IntroductionView {
        return fragment
    }

}