package com.fatcloud.account.ui.settings

import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import butterknife.OnClick
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.VibrateUtils
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.frames.network.Html5Url
import com.fatcloud.account.ui.account.WelcomeActivity
import com.fatcloud.account.ui.order.list.OrderActivity
import com.fatcloud.account.ui.settings.child.WebFragment
import com.sugar.library.ui.widget.dialog.AlertDialog
import com.sugar.library.util.CommonUtils
import kotlinx.android.synthetic.main.activity_my_order.*

/**
 * Created by Wangsw on 2020/9/21 0021 17:46.
 * </br>
 *
 */
class SettingActivity : BaseMVPActivity<SettingPresenter>(), SettingView {


    companion object {
        internal val TAB_TITLES = arrayListOf(
            StringUtils.getString(R.string.privacy_statement),
            StringUtils.getString(R.string.copyright),
            StringUtils.getString(R.string.contact_us)
        )


    }

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
                signOut()
            }

            else -> {
            }
        }
    }

    private fun signOut() {
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

    override fun loginOutSuccess() {
        startActivityClearTop(WelcomeActivity::class.java, null)
        finish()
    }

    override fun initPadLayout() {

        val action = findViewById<TextView>(R.id.tv_action)
        if (action != null) {

            action.apply {
                visibility = View.VISIBLE
                text = getString(R.string.sign_out_tv)
                setTextColor(R.color.font_white)
                setOnClickListener {
                    signOut()
                }
            }

        }

        pager.adapter = PagerAdapter(supportFragmentManager)
        tabs_type.setViewPager(pager, TAB_TITLES.toTypedArray())
        pager.offscreenPageLimit = TAB_TITLES.size
    }


    internal class PagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int) = WebFragment.newInstance(Html5Url.SPLASH_PRIVACY_STATEMENT_URL)

        override fun getCount() = OrderActivity.TAB_TITLES.size
    }

}