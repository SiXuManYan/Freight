package com.fatcloud.account.ui.task.lists.frgm

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.fatcloud.account.R
import com.fatcloud.account.common.OssUtil
import com.fatcloud.account.frames.entity.Task
import com.fatcloud.account.ui.app.CloudAccountApplication

import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.frames.glides.RoundTransFormation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_task.*

/**
 * Created by Wangsw on 2020/7/30 0030 16:13.
 * </br>
 *
 */
class TaskHolder(parent: ViewGroup?) : BaseItemViewHolder<Task>(parent, R.layout.item_home_task), LayoutContainer {

    override val containerView: View? get() = itemView

    var showTitle = false


    override fun setData(data: Task?) {

        if (data == null) {
            return
        }

        if (showTitle) {
            task_title_tv?.visibility = View.VISIBLE
        } else {
            task_title_tv?.visibility = View.GONE
        }

        OssUtil.getRealOssUrl(context, data.productIconImg, object : CloudAccountApplication.OssSignCallBack {
            override fun ossUrlSignEnd(url: String) {
                Glide.with(context).load(url).apply(RequestOptions().transform(MultiTransformation(CenterCrop(), RoundTransFormation(context, 8))))
                    .into(tag_iv)
            }

        })
        title_tv.text = if (data.productName.isNotBlank()) {
            data.productName
        } else {
            data.courseName
        }
        if (showTitle) {
            content_tv.text = data.studyDatetime
        } else {
            content_tv.visibility = View.GONE
        }


    }

}