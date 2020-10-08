package com.ftacloud.student.ui.user

import com.ftacloud.student.frames.network.response.BasePresenter
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/10/8 0008 16:26.
 * </br>
 *
 */
class UserPresenter @Inject constructor(private var view: UserView): BasePresenter(view){
}