package com.ftacloud.student.ui.course.detail.prepare.child.directory

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/11/2 0002 20:09.
 * </br>
 *
 */
@Module
class DirectoryModule {

    @Provides
    fun viewProvider(fragment: DirectoryFragment): DirectoryView {
        return fragment
    }


}