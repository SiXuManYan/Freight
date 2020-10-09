package com.ftacloud.student.ui.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import com.ftacloud.student.frames.dagger.comment.DaggerAppComponent
import com.ftacloud.student.frames.network.ApiService
import com.ftacloud.student.storage.CloudDataBase

import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.sugar.library.ui.view.refresh.CommonSmartAnimRefreshHeaderView
import com.sugar.library.ui.view.refresh.CommonSmartRefreshFooter
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/5/22 0022 15:41.
 * </br>
 * 用户端 应用模块
 */
class CloudAccountApplication : DaggerApplication(), HasActivityInjector, Application.ActivityLifecycleCallbacks, CloudAccountView {



    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            CommonSmartAnimRefreshHeaderView(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            CommonSmartRefreshFooter(context)
        }

    }


    lateinit var database: CloudDataBase @Inject set
    lateinit var apiService: ApiService @Inject set

    private val presenter by lazy { CloudAccountPresenter(this) }


    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().application(this).build()


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onActivityPaused(activity: Activity) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityStopped(activity: Activity?) = Unit

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) = Unit

    override fun onActivityResumed(activity: Activity) = Unit

    override fun onActivityStarted(activity: Activity?) = Unit

    override fun showError(code: Int, message: String) = Unit

    /*  公用网络请求   */


    var ossCallBack: OssSignCallBack? = null

    /**
     * 签名 阿里云 private image url
     */
    interface OssSignCallBack {
        fun ossUrlSignEnd(url: String)
    }

    /**
     * 获取token 签名url
     */
     fun getOssSecurityTokenForSignUrl(objectKey: String, ossCallBack: OssSignCallBack) {

        presenter.getOssSecurityTokenForSignUrl(this, objectKey, ossCallBack)
    }

}