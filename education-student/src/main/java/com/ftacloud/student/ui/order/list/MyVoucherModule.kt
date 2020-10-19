package com.ftacloud.student.ui.order.list

import dagger.Module
import dagger.Provides

@Module
class MyVoucherModule {

    @Provides
    fun viewProvider(activity: MyVoucherActivity): MyVoucherView {
        return activity
    }
}