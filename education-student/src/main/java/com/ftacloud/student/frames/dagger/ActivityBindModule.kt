package com.ftacloud.student.frames.dagger


import com.ftacloud.student.MainActivity
import com.ftacloud.student.MainModule
import com.sugar.library.frames.dagger.ActivityScore
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindModule {

    @ActivityScore
    @ContributesAndroidInjector(modules = [ MainModule::class])
    abstract fun mainActivityInjector(): MainActivity



}
