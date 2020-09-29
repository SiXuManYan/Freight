package com.ftacloud.student.ui.settings

import android.content.DialogInterface
import android.view.View
import butterknife.OnClick
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.VibrateUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.network.Html5Url
import com.ftacloud.student.ui.account.WelcomeActivity
import com.sugar.library.ui.view.dialog.AlertDialog
import com.sugar.library.util.CommonUtils

/**
 * Created by Wangsw on 2020/9/21 0021 17:46.
 * </br>
 *
 */
class SettingActivity : BaseMVPActivity<SettingPresenter>(), SettingView {
    override fun getLayoutId() = R.layout.activity_setting

    override fun initViews() {
        setMainTitle(R.string.setting)
    }

    @OnClick(
        R.id.privacy_statement_rl,
        R.id.copyright_rl,
        R.id.contact_us_rl,
        R.id.sign_out_tv


    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.privacy_statement_rl -> {
                startWebActivity(getString(R.string.privacy_statement), Html5Url.SPLASH_PRIVACY_STATEMENT_URL)
            }
            R.id.copyright_rl -> {
                startWebActivity(getString(R.string.copyright), Html5Url.COPYRIGHT_URL)
            }
            R.id.contact_us_rl -> {
                ToastUtils.showShort("联系我们")
            }
            R.id.sign_out_tv -> {
                VibrateUtils.vibrate(10)
                AlertDialog.Builder(context)
                    .setTitle(R.string.hint)
                    .setMessage(getString(R.string.login_out_hint))
                    .setPositiveButton(R.string.confirm, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, _ ->
                        dialog.dismiss()
                        presenter.loginOutRequest(this)
                    })
                    .setNegativeButton(R.string.cancel, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, _ ->
                        dialog.dismiss()
                    })
                    .create()
                    .show()
            }

            else -> {
            }
        }
    }

    override fun loginOutSuccess() {
        startActivityClearTop(WelcomeActivity::class.java,null)
        finish()
    }

}