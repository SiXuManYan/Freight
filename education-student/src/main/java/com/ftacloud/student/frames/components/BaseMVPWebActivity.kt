package com.ftacloud.student.frames.components

import android.annotation.SuppressLint
import android.net.http.SslError
import android.text.TextUtils
import android.view.View
import android.webkit.*
import com.blankj.utilcode.util.RegexUtils
import com.ftacloud.student.R
import com.sugar.library.ui.view.error.AccidentView
import com.google.gson.JsonParser
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.scwang.smart.refresh.layout.listener.ScrollBoundaryDecider
import com.sugar.library.frames.network.response.LibraryBasePresenter
import com.sugar.library.ui.view.web.JsWebView
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import kotlinx.android.synthetic.main.activity_web_common.*
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Created by Wangsw on 2019/9/3 16:55.
 * </br>
 * web 页
 */
abstract class BaseMVPWebActivity<P : LibraryBasePresenter> : BaseMVPActivity<P>(), OnRefreshListener {


    protected val jsonParser by lazy { JsonParser() }


    var contentUrl = ""

    var loadLocalHtml: Boolean = false

    /** 标题 */
    var webTitle = ""

    /** 处理返回键 */
    private var backCallJs = false


    private var isError = false

    private var changeTitle = false

    private var refresh = true

    override fun getLayoutId(): Int {
        return R.layout.activity_web_common
    }


    override fun initViews() {

        initExtra()
        initView()
    }


    private fun initExtra() {

        intent.getStringExtra(Constants.PARAM_URL)?.let {
            contentUrl = it
        }

        intent.getStringExtra(Constants.PARAM_TITLE)?.let {
            if (!TextUtils.isEmpty(it)) {
                webTitle = it
            }
        }

        intent.getBooleanExtra(Constants.PARAM_HANDLE_BACK, false).let {
            backCallJs = it
        }

        intent.getBooleanExtra(Constants.PARAM_WEB_CHANGETITLE, false).let {
            changeTitle = it
        }

        intent.getBooleanExtra(Constants.PARAM_WEB_REFRESH, true).let {
            refresh = it
        }
        intent.getBooleanExtra(Constants.PARAM_WEB_LOAD_LOCAL_HTML, false).let {
            loadLocalHtml = it
        }

    }

    @SuppressLint("JavascriptInterface")
    private fun initView() {

        tv_title.text = webTitle
        iv_back.setOnClickListener {
            onBackPressed()
        }
        title_rl.setOnClickListener {
            if (CommonUtils.isDoubleClick(it)) {
                x5_web.scrollTo(0, 0)
            }

        }

        if (refresh) {
            swipe.setEnableLoadMore(false)
            swipe.setOnRefreshListener(this)
            swipe.setScrollBoundaryDecider(object : ScrollBoundaryDecider {
                override fun canRefresh(content: View): Boolean {
                    return x5_web.scrollY <= 0
                }

                override fun canLoadMore(content: View): Boolean {
                    return false
                }
            })
        } else {
            swipe.setEnableLoadMore(false)
            swipe.setEnableRefresh(false)
        }



        accident.onRetryClickListener = object : AccidentView.OnRetryClickListener {
            override fun onRetryClick() {
                showLoadingDialog()
                onRefresh()
            }
        }
        x5_web.addJavascriptInterface(this@BaseMVPWebActivity, JsWebView.BRIDGE_NAME)

        val webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                if (!title.isNullOrEmpty() && changeTitle) {
                    tv_title.text = title
                }
            }
        }
        x5_web.webChromeClient = webChromeClient

        x5_web.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(webView: WebView?, url: String?): Boolean {
                webView?.loadUrl(url)
                return true
            }

            override fun onReceivedSslError(webView: WebView?, sslErrorHandler: SslErrorHandler, sslError: SslError?) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError)
                //  忽略SSL证书错误，继续加载页面
                sslErrorHandler.proceed()
            }

            override fun onReceivedError(p0: WebView?, p1: WebResourceRequest?, p2: WebResourceError?) {
                super.onReceivedError(p0, p1, p2)
                isError = true
            }

            override fun onPageFinished(p0: WebView?, p1: String?) {
                super.onPageFinished(p0, p1)
                if (refresh) {
                    swipe.finishRefresh()
                }
                dismissLoadingDialog()
                if (isError) {
                    accident.showRetry()
                    backCallJs = false
                    isError = false
                }
            }
        }

        // 加载Html
        x5_web.settings.defaultTextEncodingName = "utf-8";//文本编码
        x5_web.settings.domStorageEnabled = true //设置DOM存储已启用（注释后文本显示变成js代码）
        x5_web.settings.blockNetworkImage = false //设置DOM存储已启用（注释后文本显示变成js代码）


        //本地HTML里面有跨域的请求 原生webview需要设置之后才能实现跨域请求
        try {
            val clazz: Class<*> = x5_web.getSettings().javaClass
            val method: Method? = clazz.getMethod(
                "setAllowUniversalAccessFromFileURLs", Boolean::class.javaPrimitiveType
            )
            method?.invoke(x5_web.getSettings(), true)
        } catch (e: IllegalArgumentException) {

        } catch (e: NoSuchMethodException) {

        } catch (e: IllegalAccessException) {

        } catch (e: InvocationTargetException) {

        }

        x5_web.clearCache(true)
        showLoadingDialog()

        initLoadUrl()
    }

    fun initLoadUrl() {
        if (!TextUtils.isEmpty(contentUrl)) {
            if (loadLocalHtml) {
                x5_web.loadUrl("file:///android_asset/$contentUrl");
            } else {
                if (RegexUtils.isURL(contentUrl)) {
                    x5_web.loadUrl(contentUrl)

                } else {

                    x5_web.loadDataWithBaseURL(null, contentUrl, "text/html", "utf-8", null)
                }
            }

        }
    }

    override fun onBackPressed() {
        when {
            backCallJs -> x5_web.evaluateJavascript("clickLeft()")
            else -> super.onBackPressed()
        }
    }

    override fun onDestroy() {
        x5_web.destroy()
        super.onDestroy()
    }

    protected fun onRefresh() {
        if (accident.visibility == View.VISIBLE) {
            accident.hide()
        }
        if (!TextUtils.isEmpty(contentUrl)) {
            x5_web.reload()
        }
    }


    override fun onRefresh(refreshLayout: RefreshLayout) = onRefresh()


}