package com.fatcloud.account.ui.account.forget

import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.StringUtils
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.request.GetVerifyCode
import com.fatcloud.account.frames.entity.request.SetPassword
import com.fatcloud.account.frames.network.response.BasePresenter
import com.fatcloud.account.storage.entity.User
import com.google.gson.JsonElement
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.util.AndroidUtil
import com.sugar.library.util.Constants
import com.sugar.library.util.ProductUtils
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/21 0021 9:37.
 * </br>
 *
 */
class ForgetPresenter @Inject constructor(private var view: ForgetView) : BasePresenter(view) {

    fun retrieve(lifecycle: LifecycleOwner, phoneValue: String, verifyValue: String, passwordValue: String, passwordAgainValue: String) {

        val apply = SetPassword().apply {
            username = phoneValue
            passwd = AndroidUtil.md5(passwordValue)
            vc = verifyValue
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY, apiService.setPassword(apply), object : BaseHttpSubscriber<User>(view) {
            override fun onSuccess(data: User?) {
                data?.let {
                    view.retrieveSuccess()
                }
            }
        })


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