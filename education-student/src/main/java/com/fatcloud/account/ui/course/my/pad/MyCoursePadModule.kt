package com.fatcloud.account.ui.course.my.pad

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/10/29 0029 13:35.
 * </br>
 *
 */
@Module
class MyCoursePadModule {

    @Provides
    fun viewProvider(fragment: MyCoursePadFragment): MyCoursePadView {
        return fragment
    }


}