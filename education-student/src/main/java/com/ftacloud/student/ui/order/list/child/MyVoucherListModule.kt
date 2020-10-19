package com.ftacloud.student.ui.order.list.child

import dagger.Module
import dagger.Provides

@Module
class MyVoucherListModule {

    @Provides
    fun viewProvider(fragment: MyVoucherListFragment): MyVoucherListView {
        return fragment
    }
}