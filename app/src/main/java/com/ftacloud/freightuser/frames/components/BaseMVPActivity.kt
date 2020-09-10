package com.ftacloud.freightuser.frames.components

import android.content.DialogInterface
import android.os.Bundle
import com.blankj.utilcode.util.ToastUtils
import com.sugar.library.frames.network.response.BasePresenter
import com.sugar.library.frames.network.response.BaseView
import com.sugar.library.ui.view.dialog.AlertDialog
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
abstract class BaseMVPActivity<P : BasePresenter> : BaseActivity(), BaseView, HasSupportFragmentInjector {

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
                .setTitle("提示")
                .setMessage("您的账号已在其他设备登录，请重新登录")
                .setCancelable(false)
                .setPositiveButton("去登录", AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, which ->
//                        startActivityClearTop(LoginActivity::class.java,null)



                    dialog.dismiss()
                })
                .setNegativeButton("我知道了", AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
                .create()
                .show()
            return
        }
        ToastUtils.showShort(if (message.isEmpty()) "出现错误($code)" else message)
    }



}