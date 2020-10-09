package com.ftacloud.student.ui.app

import android.content.Context
import com.alibaba.sdk.android.oss.ClientConfiguration
import com.alibaba.sdk.android.oss.OSS
import com.alibaba.sdk.android.oss.OSSClient
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ThreadUtils
import com.blankj.utilcode.util.Utils
import com.ftacloud.student.frames.entity.SecurityTokenModel
import com.ftacloud.student.frames.network.ApiService
import com.sugar.library.BuildConfig

import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers


/**
 * Created by Wangsw on 2020/5/22 0022 15:52.
 * </br>
 *
 */
class CloudAccountPresenter(val view: CloudAccountView) {

    private val apiService: ApiService = (Utils.getApp() as CloudAccountApplication).apiService


    private var compositeDisposable: CompositeDisposable? = null

    fun addSubscribe(subscription: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(subscription)
    }

    private fun <T> flowableUICompose(): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    private fun <T> flowableCompose(): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
        }
    }


    /**
     * 订阅事件
     * @param consumer 处理
     */
    fun subsribeEvent(consumer: Consumer<Event>) {
        addRxBusSubscribe(Event::class.java, consumer)
    }


    /**
     * 添加Rxbus的订阅
     * @param eventType 传递类型
     * @param consumer  消费
     */
    fun <T> addRxBusSubscribe(eventType: Class<T>, consumer: Consumer<T>) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(
            RxBus.toFlowable(eventType).observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer, Consumer {
                    LogUtils.d(it)
                })
        )
    }

    // 公用网络请求

    /**
     * 获取 token
     * @param objectName 文件路径
     * @param isEncryptFile 是否为加密文件
     */
    fun getOssSecurityTokenForSignUrl(context: Context, objectKey: String, ossCallBack: CloudAccountApplication.OssSignCallBack) {


        addSubscribe(
            apiService.getOssSecurityToken().compose(flowableUICompose())
                .subscribeWith(object : BaseHttpSubscriber<SecurityTokenModel>(view) {
                    override fun onSuccess(data: SecurityTokenModel?) {
                        data?.let {
                            val runnable = Runnable {
                                // 节点
                                val endpoint = BuildConfig.OSS_END_POINT

                                val credentialProvider: OSSCredentialProvider =
                                    OSSStsTokenCredentialProvider(it.AccessKeyId, it.AccessKeySecret, it.SecurityToken)

                                val conf = ClientConfiguration().apply {
                                    connectionTimeout = 15 * 1000   // 连接超时，默认15秒
                                    socketTimeout = 15 * 1000       // socket超时，默认15秒
                                    maxConcurrentRequest = 5        // 最大并发请求数，默认5个
                                    maxErrorRetry = 2               // 失败后最大重试次数，默认2次
                                }

                                val oss: OSS = OSSClient(context, "https://$endpoint", credentialProvider, conf)

                                val url: String = oss.presignConstrainedObjectURL(it.AccessBucketName, objectKey, 30 * 60)
                                ThreadUtils.runOnUiThread {
                                    ossCallBack.ossUrlSignEnd(url)
                                }
                            }
                            Thread(runnable).start()
                        }
                    }
                })
        )


    }




}