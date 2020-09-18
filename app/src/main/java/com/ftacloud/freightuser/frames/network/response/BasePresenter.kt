package com.ftacloud.freightuser.frames.network.response

import android.content.Context
import com.ftacloud.freightuser.frames.network.ApiService
import com.ftacloud.freightuser.storage.CloudDataBase
import com.sugar.library.frames.network.response.LibraryBasePresenter
import com.sugar.library.frames.network.response.BaseView
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/10 0010 13:55.
 * </br>
 *
 */
open class BasePresenter constructor(private var view: BaseView?) : LibraryBasePresenter(view) {

    protected lateinit var apiService: ApiService @Inject set

    protected lateinit var appContext: Context @Inject set

    lateinit var database: CloudDataBase @Inject set



}