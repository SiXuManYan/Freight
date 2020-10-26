package com.ftacloud.student.ui.tests.question.holder

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.question.QuestionChild
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_question_fill.*

/**
 * Created by Wangsw on 2020/10/26 0026 15:41.
 * </br>
 *
 */
class FillHolder(parent: ViewGroup?) : BaseItemViewHolder<QuestionChild>(parent, R.layout.item_question_fill), LayoutContainer {

    override val containerView: View? get() = itemView

    override fun setData(data: QuestionChild?) {
        if (data == null) {
            return
        }
        Glide.with(context).load(data.itemImg).into(content_iv)
        phone_aet.addTextChangedListener(object : TextWatcher {


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                val answer = s.toString()
                data.nativeAnswerFill = if (answer.isBlank()) {
                    ""
                } else {
                    answer
                }
            }

        })


    }


}
