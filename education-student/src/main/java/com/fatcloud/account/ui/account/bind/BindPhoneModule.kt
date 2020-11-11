package com.fatcloud.account.ui.account.bind

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/21 0021 17:09.
 * </br>
 *
 */
@Module
class BindPhoneModule {


    @Provides
    fun viewProvider(activity: BindPhoneActivity): BindPhoneView {
        return activity
    }
}