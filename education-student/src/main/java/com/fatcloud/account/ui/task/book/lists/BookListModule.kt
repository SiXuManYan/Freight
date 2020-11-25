package com.fatcloud.account.ui.task.book.lists

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/11/24 0024 11:25.
 * </br>
 *
 */
@Module
class BookListModule {

    @Provides
    fun viewProvider(activity: BookListActivity): BookListView {
        return activity
    }

}