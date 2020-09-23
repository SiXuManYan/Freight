package com.ftacloud.student.ui.account.register

import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.network.request.GetVerifyCode
import com.ftacloud.student.frames.network.response.BasePresenter
import com.google.gson.JsonElement
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.util.Constants
import com.sugar.library.util.ProductUtils
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/21 0021 9:34.
 * </br>
 *
 */
class RegisterPresenter @Inject constructor(private var view: RegisterView) : BasePresenter(view) {

    fun register(phoneValue: String, verifyValue: String, passwordValue: String, passwordAgainValue: String) {
        if (!ProductUtils.isPhoneNumber(phoneValue)) {
            return
        }

        if (!ProductUtils.checkEmpty(verifyValue, StringUtils.getString(R.string.login_verify_empty))) {
            return
        }

        if (!ProductUtils.checkEmpty(passwordValue, StringUtils.getString(R.string.login_password_empty))) {
            return
        }

        if (passwordAgainValue.isEmpty()) {
            ToastUtils.showShort(R.string.password_input_again_hint)
            return
        }

        if (passwordValue != passwordAgainValue){
            ToastUtils.showShort(R.string.password_different_hint)
            return
        }

        view.registerSuccess()

    }



    /**
     * 获取验证码
     */
    fun getVerifyCode(lifecycle: LifecycleOwner, phoneValue: String, getVerifyTv: TextView) {

        if (!ProductUtils.isPhoneNumber(phoneValue)) {
            return
        }

        val apply = GetVerifyCode().apply {
            username = phoneValue
        }


        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.getVerifyCode(apply), object : BaseHttpSubscriber<JsonElement>(view) {
                override fun onSuccess(data: JsonElement?) {
                    view.captchaSendResult()
                    getVerifyTv.isEnabled = false
                    countdown(getVerifyTv, StringUtils.getString(R.string.get_again))
                }

                override fun onError(e: Throwable) {
                    getVerifyTv.isEnabled = true
                    super.onError(e)
                    view.captchaSendResult()
                }

            }
        )

    }


    /**
     * 倒计时
     * @param countdownView 倒计时显示的view
     * @param default       默认显示的文字
     */
    fun countdown(countdownView: TextView, default: String) {
        addSubscribe(
            Flowable.intervalRange(0, Constants.WAIT_DELAYS + 1L, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    countdownView.text = appContext.getString(R.string.count_down_format, Constants.WAIT_DELAYS - it)
                }
                .doOnComplete {
                    countdownView.text = default
                    countdownView.isEnabled = true
                }
                .subscribe())
    }



}