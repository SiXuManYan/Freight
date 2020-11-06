package com.ftacloud.student.ui.order.pay.prepare

import android.content.Intent
import android.view.View
import butterknife.OnClick
import com.blankj.utilcode.util.StringUtils
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.entity.home.HomeOrderExtra
import com.ftacloud.student.ui.order.pay.PayActivity
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
        if (!intent.extras!!.containsKey(Constants.PARAM_ORDER)) {
            val homeOrderExtra = intent.getSerializableExtra(Constants.PARAM_ORDER) as HomeOrderExtra
            setData(homeOrderExtra)
        }

    }

    private fun setData(data: HomeOrderExtra) {
        Glide.with(this).load(data.productIconImg).into(image_iv)
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
                startActivity(PayActivity::class.java)
            }
            else -> {
            }
        }
    }


}