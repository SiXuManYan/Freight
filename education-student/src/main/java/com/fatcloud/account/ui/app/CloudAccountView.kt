package com.fatcloud.account.ui.app

import com.fatcloud.account.frames.entity.AppCommon
import com.sugar.library.frames.network.response.BaseView

interface CloudAccountView : BaseView {
    fun saveCommonConfig(data: AppCommon)
}
