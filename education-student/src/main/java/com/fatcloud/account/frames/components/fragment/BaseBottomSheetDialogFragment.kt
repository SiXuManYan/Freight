package com.fatcloud.account.frames.components.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import butterknife.ButterKnife
import butterknife.Unbinder
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sugar.library.frames.network.response.LibraryBasePresenter
import com.sugar.library.frames.network.response.BaseView
import com.sugar.library.ui.widget.dialog.LoadingDialog
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * 底部弹出Fragment基类
 *
 */
abstract class BaseBottomSheetDialogFragment<P : LibraryBasePresenter> : BottomSheetDialogFragment(), BaseView, HasSupportFragmentInjector {

    private var unbinder: Unbinder? = null
    private var isViewCreated = false
    private var isViewVisible = false
    private var hasLoad = false
    private var lastClickTime = 0L
    private var clicked: HashSet<Int>? = null

    private var loadingDialog: LoadingDialog? = null

    @Inject lateinit var presenter: P
    @Inject lateinit var childFragmentInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置背景透明，显示出圆角的布局，否则会有白色底（框）
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogThemeLight)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        unbinder = ButterKnife.bind(this, view)
        initViews(view)
        isViewCreated = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lazyLoad()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun supportFragmentInjector() = childFragmentInjector

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
        presenter.detachView()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isViewVisible = userVisibleHint
        lazyLoad()
    }

    protected fun showLoadingDialog() {
        dismissLoadingDialog()
        loadingDialog = LoadingDialog.Builder(context).setCancelable(true).create()
        loadingDialog!!.show()
    }

    protected fun dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog!!.dismiss()
        }
        loadingDialog = null
    }

    override fun showError(code: Int, message: String) {
        if (code >= 0) {
            ToastUtils.showShort(if(message.isNullOrEmpty())"出现错误($code)" else message)
        } else {
            ToastUtils.showShort(message)
        }
    }

    protected fun <T> startActivity(target: Class<T>) {
        startActivity(Intent(requireContext().applicationContext, target))
    }

    protected fun <T> startActivity(target: Class<T>, bundle: Bundle) {
        startActivity(Intent(requireContext().applicationContext, target).putExtras(bundle))
    }

    protected fun <T> startActivityWithArgument(target: Class<T>, key: String, value: Any) {
        val bundle = Bundle()
        when (value) {
            is String -> bundle.putString(key, value)
            is Long -> bundle.putLong(key, value)
            is Int -> bundle.putInt(key, value)
        }
        startActivity(target, bundle)
    }

    protected fun <T> startActivityForResult(target: Class<T>, requestCode: Int) {
        startActivityForResult(Intent(requireContext().applicationContext, target), requestCode)
    }

    protected fun <T> startActivityForResultAfterLogin(target: Class<T>, bundle: Bundle, requestCode: Int) {
        if (CommonUtils.isLogin()) {
            startActivityForResult(Intent(requireContext().applicationContext, target).putExtras(bundle), requestCode)
        } else {
//            startActivity(LoginActivity::class.java)
        }
    }

    protected fun <T> startActivityAfterLogin(target: Class<T>) {
        if (CommonUtils.isLogin()) {
            startActivity(target)
        } else {
//            startActivity(LoginActivity::class.java)
        }
    }

    protected fun <T> startActivityAfterLogin(target: Class<T>, bundle: Bundle) {
        if (CommonUtils.isLogin()) {
            startActivity(target, bundle)
        } else {
//            startActivity(LoginActivity::class.java)
        }
    }

    protected fun startWebActivity(bundle: Bundle) {
//        startActivity(Intent(requireContext().applicationContext, WebCommonActivity::class.java).putExtras(bundle))
    }

    protected fun startWebActivity(title: String, url: String) {
        val bundle = Bundle()
        bundle.putString(Constants.WEB_TITLE, title)
        bundle.putString(Constants.WEB_URL, url)
        startWebActivity(bundle)
    }

    protected fun onOnceClick(view: View): Boolean {
        if (clicked == null) {
            clicked = HashSet()
        }
        if (clicked!!.contains(view.id) && (System.currentTimeMillis() - lastClickTime) < Constants.CLICK_INTERVAL) {
            return false
        }
        lastClickTime = System.currentTimeMillis()
        clicked!!.add(view.id)
        return true
    }

    private fun lazyLoad() {
        if (!hasLoad && isViewVisible && isViewCreated) {
            hasLoad = true
            loadOnVisible()
        }
    }

    /**
     * 获取布局ID
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化控件
     */
    abstract fun initViews(parent: View)

    /**
     * 延迟加载
     */
    abstract fun loadOnVisible()
}