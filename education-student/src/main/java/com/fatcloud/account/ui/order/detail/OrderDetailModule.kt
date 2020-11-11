package com.fatcloud.account.ui.order.detail

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/10/20 0020 15:14.
 * </br>
 *
 */
@Module
class OrderDetailModule {


    @Provides
    fun viewProvider(activity: OrderDetailActivity): OrderDetailView {
        return activity
    }


}