package com.sugar.library.frames.components

import android.os.Bundle
import com.sugar.library.frames.network.response.BaseView
import com.sugar.library.frames.network.response.LibraryBasePresenter
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
abstract class LibraryBaseMVPActivity<P : LibraryBasePresenter> : LibraryBaseActivity(), BaseView, HasSupportFragmentInjector {

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

    }

}