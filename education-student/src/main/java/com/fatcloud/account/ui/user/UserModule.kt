package com.fatcloud.account.ui.user

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/10/8 0008 16:26.
 * </br>
 *
 */
@Module
class UserModule  {

    @Provides
    fun viewProvider(activity: UserActivity): UserView {
        return activity
    }

}
