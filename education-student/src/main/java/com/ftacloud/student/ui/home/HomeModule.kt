package com.ftacloud.student.ui.home

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/21 0021 19:30.
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