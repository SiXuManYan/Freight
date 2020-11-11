package com.fatcloud.account.ui.task.reserve

import android.view.View
import android.view.ViewGroup
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.ReserveList

import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by Wangsw on 2020/7/30 0030 16:13.
 * </br>
 *
 */
class ReserveHolder(parent: ViewGroup?) : BaseItemViewHolder<ReserveList>(parent, R.layout.item_reserve), LayoutContainer {

    override val containerView: View? get() = itemView

    var showTitle = false



}