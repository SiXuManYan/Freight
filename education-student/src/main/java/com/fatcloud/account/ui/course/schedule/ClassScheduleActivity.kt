package com.fatcloud.account.ui.course.schedule

import android.view.ViewGroup
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.list.BaseRefreshListActivity
import com.fatcloud.account.frames.entity.ClassSchedule
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

/**
 * Created by Wangsw on 2020/9/25 0025 17:21.
 * </br>
 *  课程表
 */
class ClassScheduleActivity : BaseRefreshListActivity<ClassSchedule, ClassSchedulePresenter>(), ClassScheduleView {

    override fun getMainTitle() = R.string.class_schedule_title


    override fun getRecyclerAdapter(): RecyclerArrayAdapter<ClassSchedule> {
        val adapter = object : RecyclerArrayAdapter<ClassSchedule>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<ClassSchedule> {
                val holder = ClassScheduleHolder(parent)

                // 已开课 -> 直播
                // 未开课 -> 未开课
                return holder
            }

        }

        adapter.setOnItemClickListener {

        }
        return adapter
    }


}