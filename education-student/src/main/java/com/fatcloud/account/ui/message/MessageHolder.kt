package com.fatcloud.account.ui.message

import android.view.View
import android.view.ViewGroup
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.Message

import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_message.*

/**
 * Created by Wangsw on 2020/7/30 0030 16:13.
 * </br>
 *
 */
class MessageHolder(parent: ViewGroup?) : BaseItemViewHolder<Message>(parent, R.layout.item_message), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: Message?) {
        if (data == null) {
            return
        }
        title_tv.text = data.title
        content_tv.text = data.content

    }

}