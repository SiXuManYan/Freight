package com.ftacloud.freightuser.frames.network.response

import com.blankj.utilcode.util.Utils
import com.ftacloud.freightuser.frames.network.ApiService
import com.ftacloud.freightuser.ui.app.CloudAccountApplication
import com.sugar.library.frames.network.response.BaseNoDaggerPresenter
import com.sugar.library.frames.network.response.BaseView

/**
 * Created by Wangsw on 2020/9/10 0010 14:03.
 * </br>
 *
 */
class UserBaseNoDaggerPresenter constructor(private var view: BaseView?) : BaseNoDaggerPresenter(view) {

    protected val apiService: ApiService = (Utils.getApp() as CloudAccountApplication).apiService





}