package com.fatcloud.account.ui.account.register

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/21 0021 9:34.
 * </br>
 *
 */
@Module
class RegisterModule {

    @Provides
    fun viewProvider(activity: RegisterActivity): RegisterView {
        return activity
    }



}