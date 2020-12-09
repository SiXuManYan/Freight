package com.fatcloud.account.ui.order.pay

import android.annotation.SuppressLint
import android.os.Build
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import butterknife.OnClick
import com.alipay.sdk.app.PayTask
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.BuildConfig
import com.fatcloud.account.R
import com.fatcloud.account.common.StudentUtil
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.frames.entity.defray.AlipayResultStatus
import com.fatcloud.account.frames.entity.defray.PayResult
import com.fatcloud.account.frames.entity.defray.WechatPayInfo
import com.fatcloud.account.frames.entity.home.HomeOrderExtra
import com.fatcloud.account.ui.order.pay.result.CloudPayResultActivity
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.event.WechatPayResultEvent
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_pay.*
import java.math.BigDecimal


/**
 * Created by Wangsw on 2020/6/16 0016 16:02.
 * </br>
 * 支付状态页
 */
class PayActivity : BaseMVPActivity<PayPresenter>(), PayView {

    private var orderId = ""
    private var orderNumber = ""
    private var finalMoney = ""

    private var api: IWXAPI? = null


    var orderExtra: HomeOrderExtra? = null

    /**
     * 支付宝sdk正常运行
     */
    private val SDK_PAY_FLAG = 1

    /**
     * 支付宝支付同步结果返回，仅作为支付结束状态判断
     */
    @SuppressLint("HandlerLeak")
    private val handler = object : Handler() {

        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                SDK_PAY_FLAG -> {
                    handleAlipaySynchronizeResult(msg)
                }
                else -> {
                }
            }
        }
    }


    override fun getLayoutId(): Int = R.layout.activity_pay


    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initViews() {
        initExtra()
        initEvent()
        initView()
    }

    private fun initEvent() {
        presenter.subsribeEventEntity<WechatPayResultEvent>(Consumer {
            val resultCode = it.resultCode

            when (resultCode) {
                -2 -> {
                    ToastUtils.showShort("您已取消支付")
                }
                -1 -> {
                    ToastUtils.showShort("支付错误")
                }
                else -> {
                    presenter.checkOrderRealPaymentStatus(this, orderId, orderNumber)
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
            orderId = homeOrderExtra.orderId
            orderNumber = ""
            finalMoney = homeOrderExtra.payingMoney
        }

    }

    private fun initView() {
        setMainTitle(getString(R.string.pay_detail))
        if (finalMoney.isNotEmpty()) {
            card_money_tv.text = BigDecimal(finalMoney).toPlainString()
        }

        wechat_rb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {

                alipay_rb.isChecked = false
                StudentUtil.isWeixinAvilible(this)
            }
        }
        alipay_rb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                wechat_rb.isChecked = false
            }
        }
        wechat_rb.isChecked = true
    }


    @OnClick(
        R.id.wechat_rl,
        R.id.alipay_rl,
        R.id.pay_tv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.pay_tv -> handlePayment()
            R.id.wechat_rl -> if (!wechat_rb.isChecked) wechat_rb.isChecked = true
            R.id.alipay_rl -> if (!alipay_rb.isChecked) alipay_rb.isChecked = true

            else -> {
            }
        }
    }

    private fun handlePayment() {

        if (BuildConfig.DEBUG || BuildConfig.FLAVOR == "dev") {
            presenter.devFakerPaid(this, orderId)
            return
        }

        if (wechat_rb.isChecked) {
            if (!StudentUtil.isWeixinAvilible(this)) {
                return
            }
            presenter.wechatUnifiedOrder(this, orderId)
        } else {
            presenter.alipayUnifiedOrder(this, orderId)
        }
    }


    /**
     * 进行微信支付
     * 异步调用
     */
    override fun doWechatPay(it: WechatPayInfo) {


        api = WXAPIFactory.createWXAPI(this@PayActivity, BuildConfig.WECHAT_APPID, false).apply {
            registerApp(BuildConfig.WECHAT_APPID)
        }
        val req = PayReq().apply {
            appId = BuildConfig.WECHAT_APPID
            partnerId = it.partnerid
            prepayId = it.prepayid
            nonceStr = it.noncestr
            packageValue = it.`package`
            sign = it.sign
            timeStamp = it.timestamp
        }
        api?.sendReq(req)

    }


    /**
     * 支付宝支付，异步调用
     * @param orderInfo        app支付请求参数字符串，主要包含商户的订单信息，key=value形式，以&连接。
     * @param isShowPayLoading 用户在商户app内部点击付款，是否需要一个loading做为在钱包唤起之前的过渡，
     *                         这个值设置为true，将会在调用pay接口的时候直接唤起一个loading，
     *                         直到唤起H5支付页面或者唤起外部的钱包付款页面loading才消失。
     *                         （建议将该值设置为true，优化点击付款到支付唤起支付页面的过渡过程。）
     */
    override fun doAliPay(it: String) {
        val alipayRunnable: Runnable = Runnable {
            val alipay = PayTask(this)
            val result = alipay.payV2(it, true)
            Log.i("支付宝支付 result = ", result.toString())

            val msg = Message().apply {
                what = SDK_PAY_FLAG
                obj = result
            }
            handler.sendMessage(msg)
        }
        Thread(alipayRunnable).start()

    }


    /**
     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
     */
    @Suppress("UNCHECKED_CAST")
    private fun handleAlipaySynchronizeResult(msg: Message) {
        val payResult = PayResult(msg.obj as Map<String?, String?>)
        val resultInfo = payResult.result
        val resultStatus = payResult.resultStatus
        if (resultStatus == AlipayResultStatus.Status_6001) {
            ToastUtils.showShort("您已取消支付")
        } else {
            presenter.checkOrderRealPaymentStatus(this, orderId, orderNumber)
        }

    }

    override fun orderPaySuccess() {
        startActivity(CloudPayResultActivity::class.java)
        RxBus.post(Event(Constants.EVENT_PAY_SUCCESS))
        finish()
    }


    override fun finish() {
//        RxBus.post(Event(Constants.EVENT_CLOSE_PAY))
//        setResult(Activity.RESULT_OK)
        super.finish()
    }

    override fun checkOrderRealPaymentStatusFailure() {
//        RxBus.post(Event(Constants.EVENT_CLOSE_PAY_UNKNOWN))
//        startActivityClearTop(PayUnknownActivity::class.java, null)
        finish()

    }


}