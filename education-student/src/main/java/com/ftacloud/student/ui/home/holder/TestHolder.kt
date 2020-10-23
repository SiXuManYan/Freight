package com.ftacloud.student.ui.home.holder

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.home.Test
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_basic_test.*

/**
 * Created by Wangsw on 2020/10/13 0013 9:11.
 * </br>
 *  首页基础测试
 */
class TestHolder(parent: ViewGroup?) : BaseItemViewHolder<Test>(parent, R.layout.item_home_basic_test), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: Test?) {
        if (data == null) {
            return
        }

        title_tv.text = data.name
        Glide.with(context).load(data.iconImg).into(tag_iv)
        content_tv.text = data.introduce


    }

}