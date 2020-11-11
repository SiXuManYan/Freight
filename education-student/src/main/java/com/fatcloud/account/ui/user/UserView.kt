package com.fatcloud.account.ui.user

import android.net.Uri
import com.sugar.library.frames.network.response.BaseTaskView

/**
 * Created by Wangsw on 2020/10/8 0008 16:24.
 * </br>
 *
 */
interface UserView : BaseTaskView {
    fun onShootingPermissionResult(uri: Uri?)
    fun setUserInfoSuccess()
}