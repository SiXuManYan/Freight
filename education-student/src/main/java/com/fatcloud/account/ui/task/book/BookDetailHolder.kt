package com.fatcloud.account.ui.task.book

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
class BookDetailHolder(parent: ViewGroup?) : BaseItemViewHolder<String>(parent, R.layout.item_book_detail), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: String?) {
        if (data == null) {
            return
        }


    }


}