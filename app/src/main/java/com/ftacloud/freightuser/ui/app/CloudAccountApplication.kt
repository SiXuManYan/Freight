package com.ftacloud.freightuser.ui.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.multidex.MultiDex
import com.ftacloud.freightuser.app.DaggerAppComponent
import com.ftacloud.freightuser.network.ApiService
import com.ftacloud.freightuser.storage.CloudDataBase

import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.sugar.library.view.refresh.CommonSmartAnimRefreshHeaderView
import com.sugar.library.view.refresh.CommonSmartRefreshFooter
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
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            CommonSmartAnimRefreshHeaderView(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
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


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()



    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onActivityPaused(activity: Activity) = Unit


    override fun onActivityDestroyed(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit
    override fun onActivityStopped(activity: Activity?) {
        TODO("Not yet implemented")
    }


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityStarted(activity: Activity?) {
        TODO("Not yet implemented")
    }

    override fun showError(code: Int, message: String) {

    }


}