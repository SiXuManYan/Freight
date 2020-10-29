package com.ftacloud.student.frames.dagger


import com.ftacloud.student.ui.home.HomeFragment
import com.ftacloud.student.ui.home.HomeModule
import com.ftacloud.student.ui.order.list.child.OrderChildFragment
import com.ftacloud.student.ui.order.list.child.OrderChildModule
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


    @FragmentScope
    @ContributesAndroidInjector(modules = [OrderChildModule::class])
    abstract fun b(): OrderChildFragment


}

