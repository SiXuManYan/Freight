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
import com.ftacloud.student.ui.account.forget.ForgetActivity
import com.ftacloud.student.ui.account.forget.ForgetModule
import com.ftacloud.student.ui.course.my.detail.CourseDetailActivity
import com.ftacloud.student.ui.course.my.detail.CourseDetailModule
import com.ftacloud.student.ui.course.my.MyCourseActivity
import com.ftacloud.student.ui.course.my.MyCourseModule
import com.ftacloud.student.ui.course.schedule.ClassScheduleActivity
import com.ftacloud.student.ui.course.schedule.ClassScheduleModule
import com.ftacloud.student.ui.course.schedule.detail.NoClassActivity
import com.ftacloud.student.ui.course.schedule.detail.NoClassModule
import com.ftacloud.student.ui.message.MessageActivity
import com.ftacloud.student.ui.message.MessageModule
import com.ftacloud.student.ui.settings.SettingActivity
import com.ftacloud.student.ui.settings.SettingModule
import com.ftacloud.student.ui.task.TaskActivity
import com.ftacloud.student.ui.task.TaskModule
import com.ftacloud.student.ui.task.detail.TaskDetailActivity
import com.ftacloud.student.ui.task.detail.TaskDetailModule
import com.ftacloud.student.ui.tests.TestConditionActivity
import com.ftacloud.student.ui.tests.TestConditionModule
import com.ftacloud.student.ui.tests.my.MyTestActivity
import com.ftacloud.student.ui.tests.my.MyTestModule
import com.ftacloud.student.ui.user.UserActivity
import com.ftacloud.student.ui.user.UserModule
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
    @ContributesAndroidInjector(modules = [ForgetModule::class])
    abstract fun e(): ForgetActivity


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

    @ActivityScore
    @ContributesAndroidInjector(modules = [CourseDetailModule::class])
    abstract fun j(): CourseDetailActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [MessageModule::class])
    abstract fun k(): MessageActivity


    @ActivityScore
    @ContributesAndroidInjector(modules = [ClassScheduleModule::class])
    abstract fun l(): ClassScheduleActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [MyCourseModule::class])
    abstract fun m(): MyCourseActivity


    @ActivityScore
    @ContributesAndroidInjector(modules = [TaskDetailModule::class])
    abstract fun n(): TaskDetailActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [TaskModule::class])
    abstract fun o(): TaskActivity


    @ActivityScore
    @ContributesAndroidInjector(modules = [MyTestModule::class])
    abstract fun p(): MyTestActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [NoClassModule::class])
    abstract fun q(): NoClassActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun r(): UserActivity


}
