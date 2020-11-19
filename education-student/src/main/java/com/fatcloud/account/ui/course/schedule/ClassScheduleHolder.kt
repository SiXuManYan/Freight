package com.fatcloud.account.ui.course.schedule

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.fatcloud.account.R
import com.fatcloud.account.common.OssUtil
import com.fatcloud.account.frames.entity.ClassSchedule
import com.fatcloud.account.ui.app.CloudAccountApplication
import com.fatcloud.account.ui.course.detail.prepare.NoClassActivity
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.util.Constants
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_class_schedule.*

/**
 * Created by Wangsw on 2020/9/25 0025 17:22.
 * </br>
 *
 */
class ClassScheduleHolder(parent: ViewGroup?) : BaseItemViewHolder<ClassSchedule>(parent, R.layout.item_class_schedule), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: ClassSchedule?) {
        if (data == null) {
            return
        }

        data_tv.text = data.nativeDate

        class_container_ll.removeAllViews()
        data.nativeDataList.forEach {

            val view = View.inflate(context, R.layout.item_class_schedule_container_child, null)
            OssUtil.getRealOssUrl(context, it.productIconImg, object : CloudAccountApplication.OssSignCallBack {
                override fun ossUrlSignEnd(url: String) {
                    Glide.with(context).load(url).into(view.findViewById(R.id.image_iv))
                }
            })
            view.findViewById<AppCompatTextView>(R.id.course_name_tv).apply { text = it.courseName }
            view.findViewById<TextView>(R.id.duration_tv).apply { text = it.courseName }
            view.findViewById<TextView>(R.id.teacher_name_tv).apply { text = it.teacherName }

            view.findViewById<RelativeLayout>(R.id.container_rl).setOnClickListener { _ ->
                startActivity(NoClassActivity::class.java, Bundle().apply {
                    putString(Constants.PARAM_PRODUCT_ID, it.productId)
                })

            }
            class_container_ll.addView(view)
        }
    }


}