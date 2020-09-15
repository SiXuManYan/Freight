package com.ftacloud.freightuser.ui.ship

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/14 0014 13:35.
 * </br>
 *
 */
@Module
class ShipDetailModule {

    @Provides
    fun viewProvider(activity: ShipDetailActivity): ShipDetailView {
        return activity
    }


}