package com.fatcloud.account.ui.settings

import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import butterknife.OnClick
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.StringUtils
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
                startWebActivity(getString(R.string.privacy_statement), presenter.webRequest(0))
            }
            R.id.copyright_rl -> {
                startWebActivity(getString(R.string.copyright), presenter.webRequest(1))
            }
            R.id.contact_us_rl -> {
                startWebActivity(getString(R.string.contact_us), presenter.webRequest(2))
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
                setTextColor(ColorUtils.getColor(R.color.font_white))
                setOnClickListener {
                    signOut()
                }
            }

        }

        pager.adapter = PagerAdapter(supportFragmentManager).apply {
            this.presenter = presenter

        }
        tabs_type.setViewPager(pager, TAB_TITLES.toTypedArray())
        pager.offscreenPageLimit = TAB_TITLES.size
    }


    internal class PagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

        lateinit var presenter: SettingPresenter
        override fun getItem(position: Int): Fragment {
            return WebFragment.newInstance(presenter.webRequest(position))
        }

        override fun getCount() = OrderActivity.TAB_TITLES.size
    }


}