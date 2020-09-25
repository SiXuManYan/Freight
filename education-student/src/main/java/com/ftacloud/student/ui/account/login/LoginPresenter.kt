package com.ftacloud.student.ui.account.login

import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.StringUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.backstage.DataServiceFaker
import com.ftacloud.student.frames.network.request.FastLogin
import com.ftacloud.student.frames.network.request.GetVerifyCode
import com.ftacloud.student.frames.network.request.PasswordLogin
import com.ftacloud.student.frames.network.response.BasePresenter
import com.ftacloud.student.storage.entity.User
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import com.sugar.library.util.ProductUtils
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/*
 * Created by Wangsw on 2020/9/21 0021 9:33.
 * </br>
 *
 */
class LoginPresenter @Inject constructor(private var view: LoginView) : BasePresenter(view) {


    private val gson = Gson()

    /**
     * @param verifyChecked true 验证码登录
     *                      false 密码登录
     * @param phoneValue 手机号
     * @param verifyValue 验证码
     * @param passwordValue 密码
     */
    fun handleLogin(lifecycle: LifecycleOwner, verifyChecked: Boolean, phoneValue: String, verifyValue: String, passwordValue: String) {
        if (!ProductUtils.isPhoneNumber(phoneValue)) {
            return
        }
        if (verifyChecked) {
            verifyLogin(lifecycle, phoneValue, verifyValue)
        } else {
            passwordLogin(lifecycle, phoneValue, passwordValue)
        }

    }


    /**
     * 验证码登录
     */
    private fun verifyLogin(lifecycle: LifecycleOwner, phoneValue: String, verifyValue: String) {

        val apply = FastLogin().apply {
            username = phoneValue
            vc = verifyValue
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY, apiService.loginFast(apply), object : BaseHttpSubscriber<User>(view) {
            override fun onSuccess(data: User?) {
                data?.let {
                    loginSuccess(it,phoneValue)
                    view.loginSuccess()
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


    /**
     * 密码登录
     */
    private fun passwordLogin(lifecycle: LifecycleOwner, phoneValue: String, passwordValue: String) {

        val apply = PasswordLogin().apply {
            username = phoneValue
            passwd = passwordValue
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY, apiService.loginPassword(apply), object : BaseHttpSubscriber<User>(view) {
            override fun onSuccess(data: User?) {
                data?.let {
                    loginSuccess(it,phoneValue)
                    view.loginSuccess()
                }
            }
        })


    }





}