package com.ftacloud.student.ui.tests.question.holder

import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.question.QuestionChild
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_question_select.*

/**
 * Created by Wangsw on 2020/10/26 0026 15:41.
 * </br>
 *
 */
class SelectHolder(parent: ViewGroup?) : BaseItemViewHolder<QuestionChild>(parent, R.layout.item_question_select), LayoutContainer {

    override val containerView: View? get() = itemView




    override fun setData(data: QuestionChild?) {
        if (data == null) {
            return
        }
        Glide.with(context).load(data.itemImg).into(content_iv)

        val ownerAdapter = getOwnerAdapter<RecyclerArrayAdapter<QuestionChild>>()
        val item = ownerAdapter?.getItem(dataPosition)
        select_group.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                val checkedRadioButtonId = select_group.checkedRadioButtonId
                when (checkedRadioButtonId) {
                    a.id -> {
                        item?.nativeAnswerSelect = "A"
                    }
                    b.id -> {
                        item?.nativeAnswerSelect = "B"
                    }
                    c.id -> {
                        item?.nativeAnswerSelect = "C"
                    }
                    d.id -> {
                        item?.nativeAnswerSelect = "D"
                    }

                }
            }

        })
    }

}