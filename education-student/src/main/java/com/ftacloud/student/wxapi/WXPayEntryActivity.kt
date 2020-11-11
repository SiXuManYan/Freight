package com.ftacloud.student.wxapi

import android.content.Intent
import com.ftacloud.student.BuildConfig
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseActivity
import com.sugar.library.event.RxBus
import com.sugar.library.event.WechatPayResultEvent
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory

/**
 * Created by Wangsw on 2020/6/16 0016 15:42.
 * </br>
 * 处理微信SDK回调
 */
class WXPayEntryActivity : BaseActivity(), IWXAPIEventHandler {

    private var api: IWXAPI? = null
    private var apis: String? ="66:cd:73:62:5c:96:be:40:6e:d6:b3:95:76:d4:4c:99"

    override fun getLayoutId(): Int = R.layout.activity_wx_entry

    override fun initViews() {
        api = WXAPIFactory.createWXAPI(this, BuildConfig.WECHAT_APPID)
        api?.handleIntent(intent, this)

    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        api!!.handleIntent(intent, this)
    }



    override fun onResp(resp: BaseResp?) {
        when (resp?.type) {
            ConstantsAPI.COMMAND_PAY_BY_WX -> {
                handlePayResult(resp)
                finish()
            }
            else -> {
            }
        }

    }

    private fun handlePayResult(resp: BaseResp) {

        RxBus.post(WechatPayResultEvent(resp.errCode))
    }

    override fun onReq(p0: BaseReq?) {

    }


}