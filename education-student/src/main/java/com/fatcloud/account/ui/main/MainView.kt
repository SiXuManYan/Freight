package com.fatcloud.account.ui.main

import com.sugar.library.frames.network.response.BaseView


interface MainView : BaseView {
    fun unReadMessageCount(it: String)


}
