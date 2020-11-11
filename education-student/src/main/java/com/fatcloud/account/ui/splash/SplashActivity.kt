package com.fatcloud.account.ui.splash

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SpanUtils
import com.fatcloud.account.ui.main.MainActivity
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseActivity
import com.fatcloud.account.frames.network.Html5Url
import com.fatcloud.account.ui.account.WelcomeActivity
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import kotlinx.android.synthetic.main.activity_splash.*


/**
 *  Created by Wangsw on 2020/9/09 0025 15:44.
 * ━━━━━━━━━神兽出没━━━━━━━━━
 *
 *　　　　　　　　┏┓　　　┏┓+ +
 *　　　　　　　┏┛┻━━━┛┻┓ + +
 *　　　　　　　┃　　　　　　　┃ 　
 *　　　　　　　┃　　　━　　　┃ ++ + + +
 *　　　　　　 ████━████ ┃+
 *　　　　　　　┃　　　　　　　┃ +
 *　　　　　　　┃　　　┻　　　┃
 *　　　　　　　┃　　　　　　　┃ + +
 *　　　　　　　┗━┓　　　┏━┛
 *　　　　　　　　　┃　　　┃　　　　　　　　　　　
 *　　　　　　　　　┃　　　┃ + + + +
 *　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting　　　　　　　
 *　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug　　
 *　　　　　　　　　┃　　　┃
 *　　　　　　　　　┃　　　┃　　+　　　　　　　　　
 *　　　　　　　　　┃　 　　┗━━━┓ + +
 *　　　　　　　　　┃ 　　　　　　　┣┓
 *　　　　　　　　　┃ 　　　　　　　┏┛
 *　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 *　　　　　　　　　　┃┫┫　┃┫┫
 *　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 *
 * ━━━━━━━━━排位不跪━━━━━━━━━
 */

class SplashActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_splash

    override fun initViews() {
        if (!isTaskRoot) {
            finish()
            return
        }
        CommonUtils.setStatusBarTransparent(this)
        BarUtils.setNavBarVisibility(this, false)

        val alphaAnimation = AlphaAnimation(0f, 1f).apply {
            duration = 1000
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    initUserAgreement()
                }

                override fun onAnimationStart(p0: Animation?) {
                }
            })
        }
        splash_container_ll.startAnimation(alphaAnimation)


    }

    private fun initUserAgreement() {
        val message = SpanUtils()
            .append(getString(R.string.use_agreement_content_0))
            .append(getString(R.string.use_agreement_content_1))
            .setClickSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    startWebActivity(getString(R.string.splash_service_agreement), Html5Url.SPLASH_SERVICE_AGREEMENT)
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.color = ColorUtils.getColor(R.color.color_118EEA)
                    ds.isUnderlineText = false
                }
            })
            .append(getString(R.string.use_agreement_content_2))
            .append(getString(R.string.use_agreement_content_3))
            .setClickSpan(object : ClickableSpan() {
                override fun onClick(widget: View) =
                    startWebActivity(getString(R.string.splash_privacy_policy), Html5Url.SPLASH_PRIVACY_STATEMENT_URL)

                override fun updateDrawState(ds: TextPaint) {
                    ds.color = ColorUtils.getColor(R.color.color_118EEA)
                    ds.isUnderlineText = false
                }
            })
            .append(getString(R.string.use_agreement_content_4))
            .create()

        val ishowUserAgreement = CommonUtils.getShareStudent().getBoolean(Constants.SP_IS_SHOW_USER_AGREEMENT, false)
        if (ishowUserAgreement) {
            afterAnimation(true)
        } else {
            AlertDialog.Builder(this)
                .setTitle(R.string.tips_0)
                .setCancelable(false)
                .setMessage(message)
                .setPositiveButton(R.string.agree_and_continue) { dialog, _ ->
                    CommonUtils.getShareStudent().put(Constants.SP_IS_SHOW_USER_AGREEMENT, true)
                    afterAnimation(true)
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.disagree) { dialog, _ ->
                    CommonUtils.getShareStudent().put(Constants.SP_IS_SHOW_USER_AGREEMENT, true)
                    afterAnimation(false)
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

    private fun afterAnimation(isAgree: Boolean) {
        val isLogin = CommonUtils.getShareStudent().getBoolean(Constants.SP_LOGIN)
        if (isLogin) {

            startActivityClearTop(MainActivity::class.java, null)
        } else {
            startActivityClearTop(WelcomeActivity::class.java, null)
        }
        finish()
    }
}