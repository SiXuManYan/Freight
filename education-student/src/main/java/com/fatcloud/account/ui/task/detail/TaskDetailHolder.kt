package com.fatcloud.account.ui.task.detail

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.TaskDetail
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.frames.glides.RoundTransFormation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_reading_task_container_child.*

/**
 * Created by Wangsw on 2020/11/12 0012 10:55.
 * </br>
 *
 */
class TaskDetailHolder(parent: ViewGroup?) : BaseItemViewHolder<TaskDetail.ReadingBookOut>(parent, R.layout.item_reading_task_container_child),
    LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: TaskDetail.ReadingBookOut?) {
        if (data == null) {
            return
        }

        Glide.with(context).load(data.iconImg)
            .apply(RequestOptions().transform(MultiTransformation(CenterCrop(), RoundTransFormation(context, 8))))
            .into(image_iv)

        title_tv.text = data.name
        check_view.isChecked = data.nativeSelect
    }


}