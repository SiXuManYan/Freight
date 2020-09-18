package com.ftacloud.freightuser.frames.network.response

import android.content.Context
import com.blankj.utilcode.util.Utils
import com.ftacloud.freightuser.frames.network.ApiService
import com.ftacloud.freightuser.storage.CloudDataBase
import com.ftacloud.freightuser.ui.app.CloudAccountApplication
import com.sugar.library.frames.network.response.LibraryNoDaggerPresenter
import com.sugar.library.frames.network.response.BaseView
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/10 0010 14:03.
 * </br>
 *
 */
class NoDaggerPresenter constructor(private var view: BaseView?) : LibraryNoDaggerPresenter(view) {


    protected lateinit var apiService: ApiService @Inject set

    protected lateinit var appContext: Context @Inject set

    lateinit var database: CloudDataBase @Inject set



}