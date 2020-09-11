package com.ftacloud.freightuser.frames.components

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import butterknife.ButterKnife
import butterknife.Unbinder
import com.blankj.utilcode.util.BarUtils
import com.ftacloud.freightuser.ui.webs.WebCommonActivity
import com.sugar.library.R
import com.sugar.library.ui.view.dialog.LoadingDialog
import com.sugar.library.util.Constants


/**
 * 抽象基类
 */
abstract class BaseActivity : AppCompatActivity() {

    //Butterknife 绑定
    private var unBinder: Unbinder? = null
    private var lastClickTime = 0L
    private var clicked: HashSet<Int>? = null

    protected var context: Context? = null
    private var loadingDialog: LoadingDialog? = null


    /**
     * 获取布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化控件
     */
    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        context = this
        unBinder = ButterKnife.bind(this)

        //状态栏设置为沉浸式
/*        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            BarUtils.setStatusBarColor(this, Color.WHITE, true)
            BarUtils.setStatusBarLightMode(this, true)

            // 调整虚拟导航栏颜色
            BarUtils.setNavBarColor(this, Color.WHITE)
        } else {
            BarUtils.setStatusBarColor(this, Color.parseColor("#F6FFFFFF"), true)
        }
        BarUtils.addMarginTopEqualStatusBarHeight(findViewById(android.R.id.content))*/

        if (findViewById<View>(R.id.iv_back) != null) {
            findViewById<View>(R.id.iv_back).setOnClickListener { onBackPressed() }
        }

        initViews()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (clicked != null) {
            clicked!!.clear()
            clicked = null
        }
        unBinder?.unbind()
        loadingDialog?.let {
            if (it.isShowing)
                it.dismiss()
        }
    }


    protected fun setMainTitle(titleRes: Int) {
        if (findViewById<View>(R.id.tv_title) != null) {
            val findViewById = findViewById<TextView>(R.id.tv_title)
            findViewById.setText(titleRes)
        }
    }

    protected fun setMainTitleTextColor(colorRes: Int) {
        findViewById<TextView>(R.id.tv_title)?.setTextColor(ContextCompat.getColor(this, colorRes))
    }

    protected fun setMainTitleBackground(colorRes: Int) {
        findViewById<View>(R.id.iv_back)?.let {
            (it.parent as View).setBackgroundResource(colorRes)
        }
    }

    protected fun setBackButton(resId: Int) {
        findViewById<ImageView>(R.id.iv_back)?.setImageResource(resId)
    }

    protected fun setMainTitle(title: String) {
        if (findViewById<View>(R.id.tv_title) != null) {
            findViewById<TextView>(R.id.tv_title).text = title
        }
    }

    protected fun setTextActionButton(textRes: Int, listener: View.OnClickListener) {
        findViewById<TextView>(R.id.tv_action)?.let {
            it.visibility = View.VISIBLE
            it.setText(textRes)
            it.setOnClickListener(listener)
        }
    }

    protected fun setImageActionButton(imageRes: Int, listener: View.OnClickListener) {
        findViewById<TextView>(R.id.tv_action)?.let {
            it.visibility = View.VISIBLE
            it.setCompoundDrawablesWithIntrinsicBounds(0, imageRes, 0, 0)
            it.setOnClickListener(listener)
        }
    }

    protected fun showLoadingDialog() {
        dismissLoadingDialog()
        loadingDialog = LoadingDialog.Builder(context).setCancelable(true).create()
        loadingDialog!!.show()
    }

    protected fun showLoadingDialog(cancelable: Boolean) {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            return
        } else {
            loadingDialog = LoadingDialog.Builder(context).setCancelable(cancelable).create()
            loadingDialog!!.show()
        }

    }

    protected fun dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog!!.dismiss()
        }
        loadingDialog = null
    }

    protected fun <T> startActivity(target: Class<T>) {
        startActivity(Intent(applicationContext, target))
    }

    protected fun <T> startActivity(target: Class<T>, bundle: Bundle) {
        startActivity(Intent(applicationContext, target).putExtras(bundle))
    }

    protected fun <T> startActivityClearTop(target: Class<T>, bundle: Bundle?) {
        if (bundle == null) {
            startActivity(
                Intent(applicationContext, target).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        } else {
            startActivity(Intent(applicationContext, target).putExtras(bundle).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
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


    @SuppressLint("RestrictedApi")
    protected fun <T> startActivityForResult(target: Class<T>, requestCode: Int, bundle: Bundle?) {
        if (bundle == null) {
            startActivityForResult(Intent(applicationContext, target), requestCode)
        } else {
            startActivityForResult(
                Intent(applicationContext, target).putExtras(bundle),
                requestCode
            )
        }
    }

    /*
    protected fun <T> startActivityAfterLogin(target: Class<T>) {
        val isLogin = CommonUtils.getShareDefault().getBoolean(Constants.SP_LOGIN)
        if (isLogin) {
            startActivity(target)
        } else {
            startActivity(LoginActivity::class.java)
        }
    }

    */


    protected fun startWebActivity(bundle: Bundle) {
        startActivity(Intent(applicationContext, WebCommonActivity::class.java).putExtras(bundle))
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



    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN && currentFocus != null) {
            if (isShouldHideKeyboard(currentFocus!!, ev)) {
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    currentFocus!!.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
            }
        }
        try {
            return super.dispatchTouchEvent(ev)
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }
        return false
    }

    /**
     * 是否需要隐藏软键盘
     */
    private fun isShouldHideKeyboard(view: View, event: MotionEvent): Boolean {
        if (view is EditText) {
            val outLocation = intArrayOf(0, 0)
            view.getLocationInWindow(outLocation)
            val left = outLocation[0]
            val top = outLocation[1]
            val bottom = top + view.getHeight()
            val right = left + view.getWidth()
            return !(event.x > left && event.x < right
                    && event.y > top && event.y < bottom)
        }
        return false
    }


}