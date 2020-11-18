package com.fatcloud.account.frames.dagger


import com.fatcloud.account.ui.course.detail.prepare.child.directory.DirectoryFragment
import com.fatcloud.account.ui.course.detail.prepare.child.directory.DirectoryModule
import com.fatcloud.account.ui.course.detail.prepare.child.introduct.IntroductionFragment
import com.fatcloud.account.ui.course.detail.prepare.child.introduct.IntroductionModule
import com.fatcloud.account.ui.course.detail.prepare.child.teacher.TeacherFragment
import com.fatcloud.account.ui.course.detail.prepare.child.teacher.TeacherModule
import com.fatcloud.account.ui.course.my.child.MyCourseChildFragment
import com.fatcloud.account.ui.course.my.child.MyCourseChildModule
import com.fatcloud.account.ui.course.my.pad.MyCoursePadFragment
import com.fatcloud.account.ui.course.my.pad.MyCoursePadModule
import com.fatcloud.account.ui.home.HomeFragment
import com.fatcloud.account.ui.home.HomeModule
import com.fatcloud.account.ui.order.list.child.OrderChildFragment
import com.fatcloud.account.ui.order.list.child.OrderChildModule
import com.fatcloud.account.ui.task.lists.frgm.TaskFragment
import com.fatcloud.account.ui.task.lists.frgm.TaskModule
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


    @FragmentScope
    @ContributesAndroidInjector(modules = [MyCourseChildModule::class])
    abstract fun c(): MyCourseChildFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [DirectoryModule::class])
    abstract fun d(): DirectoryFragment


    @FragmentScope
    @ContributesAndroidInjector(modules = [TaskModule::class])
    abstract fun o(): TaskFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [IntroductionModule::class])
    abstract fun p(): IntroductionFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [TeacherModule::class])
    abstract fun q(): TeacherFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [MyCoursePadModule::class])
    abstract fun r(): MyCoursePadFragment


}

