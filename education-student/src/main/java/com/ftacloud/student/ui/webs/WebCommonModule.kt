package com.fatcloud.account.feature.webs

import com.ftacloud.student.ui.webs.WebCommonActivity
import com.ftacloud.student.ui.webs.WebCommonView
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