package com.ftacloud.student.ui.account.login

import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.CompoundButton
import butterknife.OnCheckedChanged
import butterknife.OnClick
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SpanUtils
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


    override fun getLayoutId() = R.layout.activity_login

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initViews() {

        initLoginMode()
        initEvent()
    }

    private fun initLoginMode() {

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
    }


    private fun initEvent() {
        presenter.subsribeEvent(Consumer {
            when (it.code) {
                Constants.EVENT_NEED_REFRESH -> finish()
            }
        })
    }


    override fun loginSuccess() {
        startActivityClearTop(MainActivity::class.java, null)
        RxBus.post(Event(Constants.EVENT_NEED_REFRESH))
        finish()
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

            }
            R.id.password_rb -> {
                password_rb.isChecked = true
                password_rb.textSize = 23f
                password_rb.setTextColor(ColorUtils.getColor(R.color.student_yellow))
                verify_rb.textSize = 18f
                verify_rb.setTextColor(ColorUtils.getColor(R.color.color_third_level))
                login_mode_switcher.displayedChild = 1
                span_switcher.displayedChild = 1

            }
        }
    }


    @OnClick(
        R.id.login_tv,
        R.id.register_tv,
        R.id.forget_password
    )
    fun onClick(view: View) {
        ProductUtils.handleDoubleClick(view)
        when (view.id) {
            R.id.login_tv -> {
                val verifyChecked = verify_rb.isChecked
                val phoneValue = phone_aet.text.toString().trim()
                val verifyValue = verify_code_aet.text.toString().trim()
                val passwordValue = password_aet.text.toString().trim()
                presenter.handleLogin(verifyChecked, phoneValue, verifyValue, passwordValue)
            }
            R.id.register_tv -> {
                startActivity(RegisterActivity::class.java)

            }
            R.id.forget_password -> {
                startActivity(RetrieveActivity::class.java)
            }

        }
    }


}