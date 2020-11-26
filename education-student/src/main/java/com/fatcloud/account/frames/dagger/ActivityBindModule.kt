package com.fatcloud.account.frames.dagger


import com.fatcloud.account.feature.webs.WebCommonModule
import com.fatcloud.account.ui.main.MainActivity
import com.fatcloud.account.ui.main.MainModule
import com.fatcloud.account.ui.account.WelcomeActivity
import com.fatcloud.account.ui.account.WelcomeModule
import com.fatcloud.account.ui.account.bind.BindPhoneActivity
import com.fatcloud.account.ui.account.bind.BindPhoneModule
import com.fatcloud.account.ui.account.login.LoginActivity
import com.fatcloud.account.ui.account.login.LoginModule
import com.fatcloud.account.ui.account.register.RegisterActivity
import com.fatcloud.account.ui.account.register.RegisterModule
import com.fatcloud.account.ui.account.forget.ForgetActivity
import com.fatcloud.account.ui.account.forget.ForgetModule
import com.fatcloud.account.ui.course.detail.experience.ExperienceCourseDetailActivity
import com.fatcloud.account.ui.course.detail.experience.ExperienceCourseDetailModule
import com.fatcloud.account.ui.course.schedule.ClassScheduleActivity
import com.fatcloud.account.ui.course.schedule.ClassScheduleModule
import com.fatcloud.account.ui.course.detail.prepare.NoClassActivity
import com.fatcloud.account.ui.course.detail.prepare.NoClassModule
import com.fatcloud.account.ui.course.my.MyCourseActivity
import com.fatcloud.account.ui.course.my.MyCourseModule
import com.fatcloud.account.ui.message.MessageActivity
import com.fatcloud.account.ui.message.MessageModule
import com.fatcloud.account.ui.order.detail.OrderDetailActivity
import com.fatcloud.account.ui.order.detail.OrderDetailModule
import com.fatcloud.account.ui.order.list.OrderActivity
import com.fatcloud.account.ui.order.list.OrderModule
import com.fatcloud.account.ui.order.pay.PayActivity
import com.fatcloud.account.ui.order.pay.PayModule
import com.fatcloud.account.ui.order.pay.prepare.PayPrepareActivity
import com.fatcloud.account.ui.order.pay.prepare.PayPrepareModule
import com.fatcloud.account.ui.settings.SettingActivity
import com.fatcloud.account.ui.settings.SettingModule
import com.fatcloud.account.ui.task.book.BookDetailActivity
import com.fatcloud.account.ui.task.book.BookDetailModule
import com.fatcloud.account.ui.task.book.lists.BookListActivity
import com.fatcloud.account.ui.task.book.lists.BookListModule
import com.fatcloud.account.ui.task.lists.TaskContainerActivity
import com.fatcloud.account.ui.task.lists.TaskContainerModule
import com.fatcloud.account.ui.task.detail.TaskDetailActivity
import com.fatcloud.account.ui.task.detail.TaskDetailModule
import com.fatcloud.account.ui.task.reserve.ReserveListActivity
import com.fatcloud.account.ui.task.reserve.ReserveListModule
import com.fatcloud.account.ui.tests.TestConditionActivity
import com.fatcloud.account.ui.tests.TestConditionModule
import com.fatcloud.account.ui.tests.my.MyTestActivity
import com.fatcloud.account.ui.tests.my.MyTestModule
import com.fatcloud.account.ui.tests.question.TestQuestionActivity
import com.fatcloud.account.ui.tests.question.TestQuestionModule
import com.fatcloud.account.ui.user.UserActivity
import com.fatcloud.account.ui.user.UserModule
import com.fatcloud.account.ui.webs.WebCommonActivity
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
    @ContributesAndroidInjector(modules = [ExperienceCourseDetailModule::class])
    abstract fun j(): ExperienceCourseDetailActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [MessageModule::class])
    abstract fun k(): MessageActivity


    @ActivityScore
    @ContributesAndroidInjector(modules = [ClassScheduleModule::class])
    abstract fun l(): ClassScheduleActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [FragmentBindModule::class, MyCourseModule::class])
    abstract fun m(): MyCourseActivity


    @ActivityScore
    @ContributesAndroidInjector(modules = [TaskDetailModule::class])
    abstract fun n(): TaskDetailActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [MyTestModule::class])
    abstract fun p(): MyTestActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [FragmentBindModule::class, NoClassModule::class])
    abstract fun q(): NoClassActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [UserModule::class])
    abstract fun r(): UserActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [FragmentBindModule::class, OrderModule::class])
    abstract fun s(): OrderActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [OrderDetailModule::class])
    abstract fun t(): OrderDetailActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [TestQuestionModule::class])
    abstract fun u(): TestQuestionActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [ReserveListModule::class])
    abstract fun v(): ReserveListActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [PayPrepareModule::class])
    abstract fun w(): PayPrepareActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [PayModule::class])
    abstract fun x(): PayActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [FragmentBindModule::class, TaskContainerModule::class])
    abstract fun y(): TaskContainerActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [BookListModule::class])
    abstract fun z(): BookListActivity

    @ActivityScore
    @ContributesAndroidInjector(modules = [BookDetailModule::class])
    abstract fun a1(): BookDetailActivity




}
