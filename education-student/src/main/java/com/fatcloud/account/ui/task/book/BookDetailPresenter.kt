package com.fatcloud.account.ui.task.book

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.frames.entity.BookDetail
import com.fatcloud.account.frames.entity.request.BookDetailRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/11/25 0025 9:19.
 * </br>
 *
 */
class BookDetailPresenter @Inject constructor(private var view: BookDetailView) : BasePresenter(view) {


    fun loadDetail(lifecycle: LifecycleOwner, bookId: String?) {

        val apply = BookDetailRequest().apply {
            this.buddyBookId = bookId
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.bookDetail(apply),

            object : BaseHttpSubscriber<BookDetail>(view) {
                override fun onSuccess(data: BookDetail?) {
                    if (data == null) {
                        return
                    }

                    when (data.typeValue) {
                        BookDetail.IMG -> {
                            view.bindImageDetail(data)
                        }
                        else -> {
                            view.bindMediaDetail(data)
                        }
                    }

                }
            })

    }


}