package com.ftacloud.freightuser.frames.network.response

import com.blankj.utilcode.util.Utils
import com.ftacloud.freightuser.frames.network.ApiService
import com.ftacloud.freightuser.ui.app.CloudAccountApplication
import com.sugar.library.frames.network.response.LibraryNoDaggerPresenter
import com.sugar.library.frames.network.response.LibraryBaseView

/**
 * Created by Wangsw on 2020/9/10 0010 14:03.
 * </br>
 *
 */
class UserLibraryNoDaggerPresenter constructor(private var viewLibrary: LibraryBaseView?) : LibraryNoDaggerPresenter(viewLibrary) {

    protected val apiService: ApiService = (Utils.getApp() as CloudAccountApplication).apiService





}