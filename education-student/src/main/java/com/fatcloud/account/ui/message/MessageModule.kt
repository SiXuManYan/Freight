package com.fatcloud.account.ui.message

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/25 0025 14:00.
 * </br>
 *
 */
@Module
class MessageModule {

    @Provides
    fun viewProvider(activity: MessageActivity): MessageView {
        return activity
    }

}