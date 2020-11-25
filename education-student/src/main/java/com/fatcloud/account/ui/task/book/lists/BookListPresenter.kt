package com.fatcloud.account.ui.task.book.lists

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.frames.entity.Book
import com.fatcloud.account.frames.entity.request.BookListRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.google.gson.JsonArray
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import java.util.*
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/11/24 0024 11:25.
 * </br>
 *
 */
class BookListPresenter  @Inject constructor(private var view:  BookListView) : BasePresenter(view) {


    fun loadDetail(lifecycle: LifecycleOwner, courseId: String?) {
        val apply = BookListRequest().apply {
            this.courseId = courseId
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.bookList(apply),

            object : BaseJsonArrayHttpSubscriber<Book>(view) {

                override fun onSuccess(jsonArray: JsonArray?, list: ArrayList<Book>, lastItemId: String?) {
                    view.bindList(list, lastItemId)
                }

            }
        )

    }


}