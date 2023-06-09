package com.fatcloud.account.ui.task.reserve

import com.fatcloud.account.frames.entity.Buddy
import com.fatcloud.account.frames.entity.ReserveList
import com.sugar.library.frames.network.response.list.BaseNoJsonListView2

/**
 * Created by Wangsw on 2020/11/2 0002 11:51.
 * </br>
 *
 */
interface ReserveListView : BaseNoJsonListView2<Buddy> {
    fun bookSuccess()
}