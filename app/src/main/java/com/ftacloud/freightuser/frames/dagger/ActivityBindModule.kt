package com.ftacloud.freightuser.frames.dagger

import com.ftacloud.freightuser.ui.MainActivity
import com.ftacloud.freightuser.ui.MainModule
import com.ftacloud.freightuser.ui.ship.ShipDetailActivity
import com.ftacloud.freightuser.ui.ship.ShipDetailModule
import com.sugar.library.frames.dagger.ActivityScore
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindModule {

    @ActivityScore
    @ContributesAndroidInjector(modules = [FragmentBindModule::class, MainModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [FragmentBindModule::class, ShipDetailModule::class])
    abstract fun shipDetailActivityInjector(): ShipDetailActivity


}
