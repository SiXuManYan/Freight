package com.ftacloud.student.frames.dagger


import com.ftacloud.student.MainActivity
import com.ftacloud.student.MainModule
import com.ftacloud.student.ui.account.WelcomeActivity
import com.ftacloud.student.ui.account.WelcomeModule
import com.sugar.library.frames.dagger.ActivityScore
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindModule {

    @ActivityScore
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [WelcomeModule::class])
    abstract fun welcomeActivityInjector(): WelcomeActivity


}
