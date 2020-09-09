package com.ftacloud.freightuser.ui.app

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.ftacloud.freightuser.network.ApiService

import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
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
//            RetrofitUrlManager.getInstance().putDomain(ApiService.NEW_SERVICE, UrlUtil.SERVER_HOST_V3)
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


}