package com.fatcloud.account.ui.account.login

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/21 0021 9:33.
 * </br>
 *
 */
@Module
class LoginModule {

    @Provides
    fun viewProvider(activity: LoginActivity): LoginView {
        return activity
    }


}