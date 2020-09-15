package com.ftacloud.freightuser.frames.dagger


import com.ftacloud.freightuser.ui.home.HomeFragment
import com.ftacloud.freightuser.ui.home.HomeModule
import com.sugar.library.frames.dagger.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * dagger 绑定Fragment
 */
@Module
abstract class FragmentBindModule {


    @FragmentScope
    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun homeFragmentInjector(): HomeFragment


}

