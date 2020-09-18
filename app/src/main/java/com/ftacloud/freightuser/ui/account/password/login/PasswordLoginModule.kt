package com.ftacloud.freightuser.ui.account.password.login

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/6/2 0002 18:09.
 * </br>
 *
 */
@Module
class PasswordLoginModule {

    @Provides
    fun viewProvider(activivity: PasswordLoginActivity): PasswordLoginViewLibrary {
        return activivity
    }

}