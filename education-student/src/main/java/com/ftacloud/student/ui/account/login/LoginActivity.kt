package com.ftacloud.student.ui.account.login

import android.text.Editable
import android.text.TextPaint
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.text.method.PasswordTransformationMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.CompoundButton
import butterknife.OnCheckedChanged
import butterknife.OnClick
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.MainActivity
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.network.Html5Url
import com.ftacloud.student.ui.account.register.RegisterActivity
import com.ftacloud.student.ui.account.retrieve.RetrieveActivity
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.util.Constants
import com.sugar.library.util.ProductUtils
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Wangsw on 2020/9/21 0021 9:33.
 * </br>
 *
 */
class LoginActivity : BaseMVPActivity<LoginPresenter>(), LoginView {

    /**
     * 是否为密文
     */
    private var isCipherText = true

    override fun getLayoutId() = R.layout.activity_login

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initViews() {

        initLoginMode()
        initEvent()
    }


    private fun initEvent() {
        presenter.subsribeEvent(Consumer {
            when (it.code) {
                Constants.EVENT_NEED_REFRESH -> finish()
            }
        })
    }


    private fun initLoginMode() {

        // 用户协议
        register_protocol.movementMethod = LinkMovementMethod.getInstance()
        val userAgreement = getString(R.string.login_protocol_user_agreement)
        val privacyPolicy = getString(R.string.login_protocol_privacy_policy)

        register_protocol.text = SpanUtils()
            .append(getString(R.string.login_protocol_header))
            .append(userAgreement)
            .setClickSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    startWebActivity(userAgreement, Html5Url.USER_AGREEMENT)
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.color = ColorUtils.getColor(R.color.color_118EEA)
                    ds.isUnderlineText = false
                }
            })
            .append(getString(R.string.login_protocol_header_and))
            .append(privacyPolicy)
            .setClickSpan(object : ClickableSpan() {
                override fun onClick(widget: View) = startWebActivity(privacyPolicy, Html5Url.SPLASH_PRIVACY_STATEMENT_URL)
                override fun updateDrawState(ds: TextPaint) {
                    ds.color = ColorUtils.getColor(R.color.color_118EEA)
                    ds.isUnderlineText = false
                }
            })
            .create()


        // 验证码
        verify_code_aet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                val verifyCode = s.toString().trim()
                notifyLoginButton(verifyCode)
            }

        })

        // 校验密码
        password_aet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                val password = s.toString().trim()
                notifyLoginButton(password)
            }

        })


    }

    /**
     * 更新button 状态
     */
    private fun notifyLoginButton(secondValue: String) {
        val phone = phone_aet.text.toString().trim()
        login_tv.isEnabled = phone.isNotBlank() && secondValue.isNotBlank()
    }


    private fun changeDisplayMethod() {

        if (isCipherText) {
            // 切换至明文
            password_rule_iv.setImageResource(R.mipmap.ic_temp10)
            password_aet.transformationMethod = HideReturnsTransformationMethod.getInstance();
        } else {
            // 切换至密文
            password_rule_iv.setImageResource(R.mipmap.ic_temp9)
            password_aet.transformationMethod = PasswordTransformationMethod.getInstance();
        }
        password_aet.setSelection(password_aet.text.toString().length)
        isCipherText = !isCipherText
    }


    @OnCheckedChanged(
        R.id.verify_rb,
        R.id.password_rb
    )
    fun checkedChange(buttonView: CompoundButton, isChanged: Boolean) {
        if (!isChanged) {
            return
        }
        when (buttonView.id) {
            R.id.verify_rb -> {
                verify_rb.isChecked = true
                verify_rb.textSize = 23f
                verify_rb.setTextColor(ColorUtils.getColor(R.color.student_yellow))
                password_rb.textSize = 18f
                password_rb.setTextColor(ColorUtils.getColor(R.color.color_third_level))
                login_mode_switcher.displayedChild = 0
                span_switcher.displayedChild = 0
                notifyLoginButton(verify_code_aet.text.toString().trim())

            }
            R.id.password_rb -> {
                password_rb.isChecked = true
                password_rb.textSize = 23f
                password_rb.setTextColor(ColorUtils.getColor(R.color.student_yellow))
                verify_rb.textSize = 18f
                verify_rb.setTextColor(ColorUtils.getColor(R.color.color_third_level))
                login_mode_switcher.displayedChild = 1
                span_switcher.displayedChild = 1
                notifyLoginButton(password_aet.text.toString().trim())
            }
        }
    }


    @OnClick(
        R.id.login_tv,
        R.id.register_tv,
        R.id.forget_password,
        R.id.get_verify_tv,
        R.id.password_rule_iv

    )
    fun onClick(view: View) {
        ProductUtils.handleDoubleClick(view)
        when (view.id) {
            R.id.login_tv -> {
                val verifyChecked = verify_rb.isChecked
                val phoneValue = phone_aet.text.toString().trim()
                val verifyValue = verify_code_aet.text.toString().trim()
                val passwordValue = password_aet.text.toString().trim()
                presenter.handleLogin(this, verifyChecked, phoneValue, verifyValue, passwordValue)
                loginSuccess()
            }
            R.id.register_tv -> {
                startActivity(RegisterActivity::class.java)
            }
            R.id.forget_password -> {
                startActivity(RetrieveActivity::class.java)
            }
            R.id.get_verify_tv -> {
                val phoneValue = phone_aet.text.toString().trim()
                presenter.getVerifyCode(this, phoneValue, get_verify_tv)
            }
            R.id.password_rule_iv -> {
                changeDisplayMethod()
            }

        }
    }


    override fun loginSuccess() {
        startActivityClearTop(MainActivity::class.java, null)
        RxBus.post(Event(Constants.EVENT_NEED_REFRESH))
        finish()
    }

    override fun captchaSendResult() {
        ToastUtils.showShort(R.string.captcha_target_format)
    }


}