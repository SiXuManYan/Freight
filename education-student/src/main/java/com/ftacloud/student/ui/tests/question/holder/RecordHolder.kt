package com.ftacloud.student.ui.tests.question.holder

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.question.QuestionChild
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_question_record.*

/**
 * Created by Wangsw on 2020/10/26 0026 15:45.
 * </br>
 *
 */
class RecordHolder(parent: ViewGroup?) : BaseItemViewHolder<QuestionChild>(parent, R.layout.item_question_record), LayoutContainer {

    override val containerView: View? get() = itemView

    override fun setData(data: QuestionChild?) {
        if (data == null) {
            return
        }
        Glide.with(context).load(data.itemImg).into(content_iv)
    }


}