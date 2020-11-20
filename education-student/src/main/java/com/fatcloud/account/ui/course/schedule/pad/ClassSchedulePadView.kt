package com.fatcloud.account.ui.course.schedule.pad

import com.fatcloud.account.frames.entity.MyCourse
import com.sugar.library.frames.network.response.BaseView
import java.util.ArrayList

/**
 * Created by Wangsw on 2020/11/19 0019 14:21.
 * </br>
 *
 */
interface ClassSchedulePadView :BaseView {
    fun bindList(list: ArrayList<MyCourse>, lastItemId: String?)
}