package com.fatcloud.account.frames.components

import android.content.DialogInterface
import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.R
import com.fatcloud.account.ui.account.WelcomeActivity
import com.sugar.library.frames.network.response.LibraryBasePresenter
import com.sugar.library.frames.network.response.BaseView
import com.sugar.library.ui.widget.dialog.AlertDialog
import com.sugar.library.util.Constants
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * MVP框架Activity基类
 */
abstract class BaseMVPActivity<P : LibraryBasePresenter> : BaseActivity(), BaseView, HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    @Inject
    public lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        presenter.subsribeEvent(Consumer {
            if (it.code == Constants.EVENT_FINISH_ALL) {
                finish()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> {
        return fragmentInjector
    }


    override fun showError(code: Int, message: String) {
        if (code < 0) {
            ToastUtils.showShort(message)
            return
        }
        if (code == 401) {
            AlertDialog.Builder(this)
                .setTitle(R.string.hint)
                .setMessage(getString(R.string.remote_login))
                .setCancelable(false)
                .setPositiveButton(R.string.go_login, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, which ->
                    startActivityClearTop(WelcomeActivity::class.java, null)
                    dialog.dismiss()
                })
                .setNegativeButton(R.string.i_know, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                .create()
                .show()
            return
        }
        ToastUtils.showShort(if (message.isEmpty()) getString(R.string.error_message_format, code) else message)
    }


}