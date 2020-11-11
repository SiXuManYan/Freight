package com.fatcloud.account.ui.order.list.child

import dagger.Module
import dagger.Provides

@Module
class OrderChildModule {

    @Provides
    fun viewProvider(fragment: OrderChildFragment): OrderChildView {
        return fragment
    }
}