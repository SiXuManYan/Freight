package com.fatcloud.account.ui.settings

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/21 0021 17:45.
 * </br>
 *
 */
@Module
class SettingModule {

    @Provides
    fun viewProvider(activity: SettingActivity): SettingView {
        return activity
    }

}