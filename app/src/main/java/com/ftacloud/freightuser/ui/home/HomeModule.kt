package com.ftacloud.freightuser.ui.home

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/14 0014 10:32.
 * </br>
 *
 */

@Module
class HomeModule {

    @Provides
    fun viewProvider(fragment: HomeFragment): HomeView {
        return fragment
    }

}