package com.ftacloud.student.frames.dagger


import com.fatcloud.account.feature.webs.WebCommonModule
import com.ftacloud.student.MainActivity
import com.ftacloud.student.MainModule
import com.ftacloud.student.ui.account.WelcomeActivity
import com.ftacloud.student.ui.account.WelcomeModule
import com.ftacloud.student.ui.account.bind.BindPhoneActivity
import com.ftacloud.student.ui.account.bind.BindPhoneModule
import com.ftacloud.student.ui.account.login.LoginActivity
import com.ftacloud.student.ui.account.login.LoginModule
import com.ftacloud.student.ui.account.register.RegisterActivity
import com.ftacloud.student.ui.account.register.RegisterModule
import com.ftacloud.student.ui.account.retrieve.RetrieveActivity
import com.ftacloud.student.ui.account.retrieve.RetrieveModule
import com.ftacloud.student.ui.settings.SettingActivity
import com.ftacloud.student.ui.settings.SettingModule
import com.ftacloud.student.ui.tests.TestConditionActivity
import com.ftacloud.student.ui.tests.TestConditionModule
import com.ftacloud.student.ui.webs.WebCommonActivity
import com.sugar.library.frames.dagger.ActivityScore
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBindModule {

    @ActivityScore
    @ContributesAndroidInjector(modules = [FragmentBindModule::class, MainModule::class])
    abstract fun a(): MainActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [WelcomeModule::class])
    abstract fun b(): WelcomeActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun c(): LoginActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [RegisterModule::class])
    abstract fun d(): RegisterActivity


    @ActivityScore
    @ContributesAndroidInjector(modules = [RetrieveModule::class])
    abstract fun e(): RetrieveActivity


    @ActivityScore
    @ContributesAndroidInjector(modules = [WebCommonModule::class])
    abstract fun f(): WebCommonActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [BindPhoneModule::class])
    abstract fun g(): BindPhoneActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [SettingModule::class])
    abstract fun h(): SettingActivity


    @ActivityScore
    @ContributesAndroidInjector(modules = [TestConditionModule::class])
    abstract fun i(): TestConditionActivity


}
