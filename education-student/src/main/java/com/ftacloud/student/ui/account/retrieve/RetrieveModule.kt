package com.ftacloud.student.ui.account.retrieve

import com.ftacloud.student.ui.account.register.RegisterActivity
import com.ftacloud.student.ui.account.register.RegisterView
import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/21 0021 9:37.
 * </br>
 *
 */
@Module
class RetrieveModule {


    @Provides
    fun viewProvider(activity: RetrieveActivity): RetrieveView {
        return activity
    }

}