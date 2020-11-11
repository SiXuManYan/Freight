package com.fatcloud.account.ui.account.forget

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/21 0021 9:37.
 * </br>
 *
 */
@Module
class ForgetModule {


    @Provides
    fun viewProvider(activity: ForgetActivity): ForgetView {
        return activity
    }

}