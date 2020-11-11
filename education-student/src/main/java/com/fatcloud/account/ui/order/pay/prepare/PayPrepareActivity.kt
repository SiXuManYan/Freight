package com.fatcloud.account.ui.order.pay.prepare

import android.os.Bundle
import android.view.View
import butterknife.OnClick
import com.blankj.utilcode.util.StringUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.fatcloud.account.R
import com.fatcloud.account.common.OssUtil
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.frames.entity.home.HomeOrderExtra
import com.fatcloud.account.ui.app.CloudAccountApplication
import com.fatcloud.account.ui.order.pay.PayActivity
import com.sugar.library.frames.glides.RoundTransFormation
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_pay_prepare.*

/**
 * Created by Wangsw on 2020/11/3 0017 9:45.
 * </br>
 * 支付过度页
 */
class PayPrepareActivity : BaseMVPActivity<PayPreparePresenter>(), PayPrepareView {

    var orderExtra: HomeOrderExtra? = null

    override fun getLayoutId(): Int = R.layout.activity_pay_prepare

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initViews() {

        initExtra()
        initEvent()
    }


    private fun initEvent() {

        presenter.subsribeEvent(Consumer {
            when (it.code) {
                Constants.EVENT_CLOSE_PAY_UNKNOWN -> {
                    finish()
                }
                else -> {
                }
            }
        })

    }

    private fun initExtra() {
        if (intent.extras == null) {
            finish()
            return
        }
        if (intent.extras!!.containsKey(Constants.PARAM_ORDER)) {
            val homeOrderExtra = intent.getSerializableExtra(Constants.PARAM_ORDER) as HomeOrderExtra
            this.orderExtra = homeOrderExtra
            setData(homeOrderExtra)
        }

    }

    private fun setData(data: HomeOrderExtra) {
        OssUtil.getRealOssUrl(this, data.productIconImg, object : CloudAccountApplication.OssSignCallBack {
            override fun ossUrlSignEnd(url: String) {
                Glide.with(this@PayPrepareActivity).load(url)
                    .apply(RequestOptions().transform(MultiTransformation(CenterCrop(), RoundTransFormation(context, 8)))).into(image_iv)
            }

        })

        title_tv.text = data.productName
        content_tv.text = data.productIntroduce
        money_tv.text = StringUtils.getString(R.string.money_symbol_format_with_blank, data.productMoney)
    }


    @OnClick(
        R.id.pay_tv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.pay_tv -> {
                startActivity(PayActivity::class.java, Bundle().apply {
                    putSerializable(Constants.PARAM_ORDER, orderExtra)
                })
                finish()
            }
            else -> {
            }
        }
    }


}