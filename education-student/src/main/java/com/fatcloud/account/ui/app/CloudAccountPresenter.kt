package com.fatcloud.account.ui.app

import android.content.Context
import android.util.Log
import androidx.annotation.IdRes
import com.alibaba.sdk.android.oss.*
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider
import com.alibaba.sdk.android.oss.model.PutObjectRequest
import com.alibaba.sdk.android.oss.model.PutObjectResult
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ThreadUtils
import com.blankj.utilcode.util.Utils
import com.fatcloud.account.frames.entity.SecurityTokenModel
import com.fatcloud.account.frames.network.ApiService
import com.sugar.library.BuildConfig
import com.sugar.library.event.Event
import com.sugar.library.event.ImageUploadEvent
import com.sugar.library.event.RecordUploadEvent
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
     * 获取 token 签名 url
     * @param objectName 文件路径
     * @param isEncryptFile 是否为加密文件
     */
    fun getOssSecurityTokenForSignUrl(context: Context, objectKey: String, ossCallBack: CloudAccountApplication.OssSignCallBack) {

        /*
        val oldOssToken = CommonUtils.getShareStudent().getString(Constants.SP_TOKEN, "")
        val oldOssTokenTime = CommonUtils.getShareStudent().getLong(Constants.SP_TOKEN_OSS_TIME, 0)

        // token 过期时间 一小时
        if (System.currentTimeMillis() - oldOssTokenTime <= 1000 * 60 * 60) {
            // 一小时以内，可以使用旧token

        }
*/

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


    /**
     * 获取 token
     * @param objectName 文件路径
     * @param isEncryptFile 是否为加密文件
     */
    fun getOssSecurityToken(context: Context, localFilePatch: String, @IdRes fromViewId: Int, clx: Class<*>) {

        addSubscribe(
            apiService.getOssSecurityToken().compose(flowableUICompose())
                .subscribeWith(object : BaseHttpSubscriber<SecurityTokenModel>(view) {
                    override fun onSuccess(data: SecurityTokenModel?) {
                        data?.let {
                            val runnable = Runnable {
                                uploadResources(context, it, localFilePatch, fromViewId, clx)
                            }
                            Thread(runnable).start()
                        }
                    }
                })
        )
    }


    /**
     * 上传图片资源至阿里云
     * @see {@see https://github.com/aliyun/aliyun-oss-android-sdk/blob/master/README-CN.md}
     * @param isEncryptFile 是否为加密上传
     */
    fun uploadResources(context: Context, stsModel: SecurityTokenModel, localFilePatch: String, @IdRes fromViewId: Int, clx: Class<*>) {

        // 节点
        val endpoint = BuildConfig.OSS_END_POINT

        // bucketName
        val imageBucketName = stsModel.AccessBucketName

        // 自定义文件的 objectKey 上传到仓库中哪个位置
        val imageObjectKey = if (BuildConfig.FLAVOR == "dev") {
            StringUtils.getString(com.sugar.library.R.string.upload_image_encryption_path_dev_format, System.currentTimeMillis().toString())
        } else {
            StringUtils.getString(com.sugar.library.R.string.upload_image_encryption_path_format, System.currentTimeMillis().toString())
        }

        val credentialProvider: OSSCredentialProvider =
            OSSStsTokenCredentialProvider(stsModel.AccessKeyId, stsModel.AccessKeySecret, stsModel.SecurityToken)

        val conf = ClientConfiguration().apply {
            connectionTimeout = 15 * 1000   // 连接超时，默认15秒
            socketTimeout = 15 * 1000       // socket超时，默认15秒
            maxConcurrentRequest = 5        // 最大并发请求数，默认5个
            maxErrorRetry = 2               // 失败后最大重试次数，默认2次
        }

//        OSSLog.enableLog() //这个开启会支持写入手机sd卡中的一份日志文件位置在SDCard_path\OSSLog\logs.csv

        //初始化OSS服务的客户端oss
        //事实上，初始化OSS的实例对象，应该具有与整个应用程序相同的生命周期，在应用程序生命周期结束时销毁
        val oss: OSS = OSSClient(context, "https://$endpoint", credentialProvider, conf)

        // 构造上传请求,第二个数参是ObjectName，第三个参数是本地文件路径
        val put = PutObjectRequest(imageBucketName, imageObjectKey, localFilePatch)
        put.progressCallback = OSSProgressCallback<PutObjectRequest> { request, currentSize, totalSize ->
//            Log.i("上传进度：", "当前进度$currentSize   总进度$totalSize");
        }

        val task = oss.asyncPutObject(put, object : OSSCompletedCallback<PutObjectRequest, PutObjectResult> {
            override fun onSuccess(request: PutObjectRequest?, result: PutObjectResult?) {
//                Log.d("PutObject", "UploadSuccess");

                var finalUrl = ""
                finalUrl = imageObjectKey

                RxBus.post(ImageUploadEvent(finalUrl, fromViewId, clx))
            }

            override fun onFailure(request: PutObjectRequest?, clientException: ClientException?, serviceException: ServiceException?) {
                clientException?.printStackTrace()

            }

        })

        // 等异步上传过程完成
        task.waitUntilFinished();
//        Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
    }


    fun getRecordToken(context: Context, localFilePatch: String, position: Int) {

        addSubscribe(
            apiService.getOssSecurityToken().compose(flowableUICompose())
                .subscribeWith(object : BaseHttpSubscriber<SecurityTokenModel>(view) {
                    override fun onSuccess(data: SecurityTokenModel?) {
                        data?.let {
                            val runnable = Runnable {
                                uploadRecord(context, it, localFilePatch, position)
                            }
                            Thread(runnable).start()
                        }
                    }
                })
        )

    }


    /**
     * 上传图片资源至阿里云
     * @see {@see https://github.com/aliyun/aliyun-oss-android-sdk/blob/master/README-CN.md}
     * @param isEncryptFile 是否为加密上传
     */
    fun uploadRecord(context: Context, stsModel: SecurityTokenModel, localFilePatch: String, position: Int) {

        // 节点
        val endpoint = BuildConfig.OSS_END_POINT

        // bucketName
        val imageBucketName = stsModel.AccessBucketName

        // 自定义文件的 objectKey 上传到仓库中哪个位置
        val imageObjectKey = if (BuildConfig.FLAVOR == "dev") {
            StringUtils.getString(com.sugar.library.R.string.upload_record_dev_format, System.currentTimeMillis().toString())
        } else {
            StringUtils.getString(com.sugar.library.R.string.upload_record_format, System.currentTimeMillis().toString())
        }


        val credentialProvider: OSSCredentialProvider =
            OSSStsTokenCredentialProvider(stsModel.AccessKeyId, stsModel.AccessKeySecret, stsModel.SecurityToken)

        val conf = ClientConfiguration()


        val oss: OSS = OSSClient(context, "https://$endpoint", credentialProvider, conf)

        val put = PutObjectRequest(imageBucketName, imageObjectKey, localFilePatch)
        put.progressCallback = OSSProgressCallback<PutObjectRequest> { request, currentSize, totalSize ->
            Log.i("录音上传进度：", "当前进度$currentSize   总进度$totalSize");
        }

        val task = oss.asyncPutObject(put, object : OSSCompletedCallback<PutObjectRequest, PutObjectResult> {
            override fun onSuccess(request: PutObjectRequest?, result: PutObjectResult?) {
                var finalUrl = ""
                finalUrl = imageObjectKey
                RxBus.post(RecordUploadEvent(finalUrl, position))
            }

            override fun onFailure(request: PutObjectRequest?, clientException: ClientException?, serviceException: ServiceException?) {
                clientException?.printStackTrace()
            }
        })

        // 等异步上传过程完成
        task.waitUntilFinished();

    }


}