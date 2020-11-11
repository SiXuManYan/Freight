package com.fatcloud.account.ui.account

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/18 0018 15:20.
 * </br>
 *
 */
@Module
class WelcomeModule {

    @Provides
    fun viewProvider(activity: WelcomeActivity): WelcomeView {
        return activity
    }

}