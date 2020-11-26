package com.fatcloud.account.ui.task.book

import com.fatcloud.account.frames.entity.BookDetail
import com.sugar.library.frames.network.response.BaseTaskView

/**
 * Created by Wangsw on 2020/11/25 0025 9:18.
 * </br>
 *
 */
interface BookDetailView : BaseTaskView {
    fun bindMediaDetail(data: BookDetail)
    fun bindImageDetail(data: BookDetail)

}