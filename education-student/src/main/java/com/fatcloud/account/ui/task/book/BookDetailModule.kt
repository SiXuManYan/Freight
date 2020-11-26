package com.fatcloud.account.ui.task.book

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/11/25 0025 9:19.
 * </br>
 *
 */
@Module
class BookDetailModule {



    @Provides
    fun viewProvider(activity: BookDetailActivity): BookDetailView {
        return activity
    }


}