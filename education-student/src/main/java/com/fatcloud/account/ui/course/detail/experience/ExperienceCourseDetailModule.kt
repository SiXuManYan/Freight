package com.fatcloud.account.ui.course.detail.experience

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/23 0023 9:56.
 * </br>
 *
 */
@Module
class ExperienceCourseDetailModule {


    @Provides
    fun viewProvider(activity: ExperienceCourseDetailActivity): ExperienceCourseDetailView {
        return activity
    }

}