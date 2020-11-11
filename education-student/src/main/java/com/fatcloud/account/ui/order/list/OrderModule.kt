package com.fatcloud.account.ui.order.list

import dagger.Module
import dagger.Provides

@Module
class OrderModule {

    @Provides
    fun viewProvider(activity: OrderActivity): OrderView {
        return activity
    }
}