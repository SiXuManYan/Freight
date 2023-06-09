package com.fatcloud.account.ui.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.LogUtils
import com.didichuxing.doraemonkit.DoraemonKit
import com.fatcloud.account.frames.dagger.comment.DaggerAppComponent
import com.fatcloud.account.frames.entity.AppCommon
import com.fatcloud.account.frames.network.ApiService
import com.fatcloud.account.frames.pushs.NotificationUtil
import com.fatcloud.account.storage.CloudDataBase
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.sugar.library.ui.widget.refresh.CommonSmartAnimRefreshHeaderView
import com.sugar.library.ui.widget.refresh.CommonSmartRefreshFooter
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
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
        presenter.requestCommon(this)
        registerActivityLifecycleCallbacks(this)
        DoraemonKit.install(this, "785a6de631aec3158d22b2954ebae1d7")
        NotificationUtil.initCloudChannel(this)
        initX5WebView()
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

    public var data: AppCommon? = null

    override fun saveCommonConfig(data: AppCommon) {
        this.data = data
    }


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


    /**
     * 获取 token
     * @param objectName 文件路径
     * @param isEncryptFile 是否为加密文件
     * @param isFaceUp 是否为证件照上传
     * @param localFilePatch 图片所在本地路径
     * @param fromViewId 发起请求的viewId 用于上传成功后，做后续操作
     * @param clx 请求发起位置
     *
     */
    fun getOssSecurityToken(localFilePatch: String, @IdRes fromViewId: Int, clx: Class<*>) {
        presenter.getOssSecurityToken(this, localFilePatch, fromViewId, clx)
    }

    fun uploadRecord(localFilePatch: String, position: Int) {
        presenter.getRecordToken(this, localFilePatch, position)
    }


    /**
     * 初始化X5内核
     */
    private fun initX5WebView() {

        // 首次初始化冷启动优化。在调用TBS初始化、创建WebView之前进行如下配置
        val map = HashMap<String, Any>()
        map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
        map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
        QbSdk.initTbsSettings(map)

        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initX5Environment(this, object : QbSdk.PreInitCallback {

            override fun onViewInitFinished(p0: Boolean) {
                LogUtils.e("X5_WebView", "加载内核是否成功:$p0")
            }

            override fun onCoreInitFinished() {

            }
        })
    }


}
