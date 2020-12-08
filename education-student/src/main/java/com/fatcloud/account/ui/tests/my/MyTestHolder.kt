package com.fatcloud.account.ui.tests.my

import android.view.View
import android.view.ViewGroup
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.question.my.MyQuestion
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_my_test.*

/**
 * Created by Wangsw on 2020/7/30 0030 16:13.
 * </br>
 *
 */
class MyTestHolder(parent: ViewGroup?) : BaseItemViewHolder<MyQuestion>(parent, R.layout.item_my_test), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: MyQuestion?) {
        if (data == null) {
            return
        }
        title_tv.text = data.quizzes?.name
        content_tv.text = data.quizzes?.introduce
    }

}