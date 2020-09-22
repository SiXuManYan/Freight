package com.ftacloud.student.frames.dagger


import com.ftacloud.student.ui.home.HomeFragment
import com.ftacloud.student.ui.home.HomeModule
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
    abstract fun a(): HomeFragment



}

