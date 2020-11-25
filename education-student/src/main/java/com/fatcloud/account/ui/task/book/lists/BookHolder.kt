package com.fatcloud.account.ui.task.book.lists

import android.view.View
import android.view.ViewGroup
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.Book
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_book.*

/**
 * Created by Wangsw on 2020/11/12 0012 10:55.
 * </br>
 *
 */
class BookHolder(parent: ViewGroup?) : BaseItemViewHolder<Book>(parent, R.layout.item_book), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: Book?) {
        if (data == null) {
            return
        }
        index_tv.text = dataPosition.toString()
        course_directory_title.text = data.name

    }


}