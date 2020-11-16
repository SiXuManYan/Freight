package com.fatcloud.account.ui.task.detail

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.room.util.StringUtil
import com.blankj.utilcode.util.StringUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.TaskDetail
import com.fatcloud.account.ui.task.progress.BookProgressActivity
import com.fatcloud.account.ui.task.reserve.ReserveListActivity
import com.fatcloud.account.ui.task.result.BookResultActivity
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.frames.glides.RoundTransFormation
import com.sugar.library.util.Constants
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
        index_tv.text = dataPosition.toString()
        course_directory_title.text = data.courseName
        duration.text = StringUtils.getString(R.string.chapter_format, data.lengthOfCourse)

        reserve_tv.setOnClickListener {
            when {
                data.scheduleStateText.contains("未激活") -> {
                    startActivity(ReserveListActivity::class.java, Bundle().apply {
                        putString(Constants.PARAM_SCHEDULE_ID, data.scheduleId)
                    })
                }
                data.scheduleStateText.contains("预约中") -> {
                    startActivity(BookProgressActivity::class.java)
                }
                data.scheduleStateText.contains("预约成功") -> {
                    startActivity(BookResultActivity::class.java)
                }
            }
        }
    }


}