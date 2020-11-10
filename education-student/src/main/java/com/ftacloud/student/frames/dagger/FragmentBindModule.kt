package com.ftacloud.student.frames.dagger


import com.ftacloud.student.ui.course.detail.prepare.child.directory.DirectoryFragment
import com.ftacloud.student.ui.course.detail.prepare.child.directory.DirectoryModule
import com.ftacloud.student.ui.course.detail.prepare.child.introduct.IntroductionFragment
import com.ftacloud.student.ui.course.detail.prepare.child.introduct.IntroductionModule
import com.ftacloud.student.ui.course.detail.prepare.child.teacher.TeacherFragment
import com.ftacloud.student.ui.course.detail.prepare.child.teacher.TeacherModule
import com.ftacloud.student.ui.course.my.child.MyCourseChildFragment
import com.ftacloud.student.ui.course.my.child.MyCourseChildModule
import com.ftacloud.student.ui.home.HomeFragment
import com.ftacloud.student.ui.home.HomeModule
import com.ftacloud.student.ui.order.list.child.OrderChildFragment
import com.ftacloud.student.ui.order.list.child.OrderChildModule
import com.ftacloud.student.ui.task.TaskFragment
import com.ftacloud.student.ui.task.TaskModule
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


}

