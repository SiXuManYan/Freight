package com.ftacloud.freightuser.frames.network.response

import android.content.Context
import com.ftacloud.freightuser.frames.network.ApiService
import com.sugar.library.frames.network.response.BasePresenter
import com.sugar.library.frames.network.response.BaseView
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/10 0010 13:55.
 * </br>
 *
 */
open class UserBasePresenter constructor(private var view: BaseView?) : BasePresenter(view) {

    protected lateinit var apiService: ApiService @Inject set

    protected lateinit var appContext: Context @Inject set





}