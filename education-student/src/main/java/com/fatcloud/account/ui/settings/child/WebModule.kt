package com.fatcloud.account.ui.settings.child

import com.fatcloud.account.ui.settings.SettingActivity
import com.fatcloud.account.ui.settings.SettingView
import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/12/9 0009 9:48.
 * </br>
 *
 */
@Module
class WebModule {

    @Provides
    fun viewProvider(fragment: WebFragment): WebFragmentView {
        return fragment
    }
}