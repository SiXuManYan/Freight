package com.fatcloud.account.feature.webs

import com.fatcloud.account.ui.webs.WebCommonActivity
import com.fatcloud.account.ui.webs.WebCommonView
import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/06/01
 * </br>
 *
 */
@Module
class WebCommonModule {

    @Provides
    fun viewProvider(activity: WebCommonActivity): WebCommonView {
        return activity
    }

}