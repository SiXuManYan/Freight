package com.fatcloud.account.frames.network.response

import android.content.Context
import com.fatcloud.account.frames.network.ApiService
import com.fatcloud.account.storage.CloudDataBase
import com.sugar.library.frames.network.response.BaseView
import com.sugar.library.frames.network.response.LibraryNoDaggerPresenter
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