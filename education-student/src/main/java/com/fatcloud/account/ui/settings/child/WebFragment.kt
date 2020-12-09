package com.fatcloud.account.ui.settings.child

import android.net.http.SslError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.fragment.BaseFragment
import com.sugar.library.util.Constants
import kotlinx.android.synthetic.main.fragment_web.*

/**
 * Created by Wangsw on 2020/11/5 0005 10:56.
 * </br>
 *
 */
class WebFragment : BaseFragment<WebPresenter>(), WebFragmentView {


    companion object {

        /**
         * @param categoryValue tab类别，全部时传空
         */
        fun newInstance(url: String): WebFragment {
            val fragment = WebFragment()
            val args = Bundle()
            args.putString(Constants.PARAM_URL, url)
            fragment.arguments = args
            return fragment
        }

    }


    override fun getLayoutId() = R.layout.fragment_web

    override fun loadOnVisible() {
        TODO("Not yet implemented")
    }

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun initViews(parent: View) {

        js_wb.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(webView: WebView?, url: String?): Boolean {
                webView?.loadUrl(url)
                return true
            }

            override fun onReceivedSslError(webView: WebView?, sslErrorHandler: SslErrorHandler, sslError: SslError?) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError)
                //  忽略SSL证书错误，继续加载页面
                sslErrorHandler.proceed()
            }
        }

        // 加载Html
        js_wb.settings.defaultTextEncodingName = "utf-8";//文本编码
        js_wb.settings.domStorageEnabled = true //设置DOM存储已启用（注释后文本显示变成js代码）
        js_wb.settings.blockNetworkImage = false //设置DOM存储已启用（注释后文本显示变成js代码）


        val url = arguments?.getString(Constants.PARAM_URL, "")
        url?.let {
            js_wb.loadUrl("https://www.baidu.com")
        }
    }

}