package com.ftacloud.student.ui.course.schedule

import com.ftacloud.student.R
import com.ftacloud.student.frames.components.list.BaseRefreshListActivity
import com.ftacloud.student.frames.entity.ClassSchedule
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

/**
 * Created by Wangsw on 2020/9/25 0025 17:21.
 * </br>
 *  课程表
 */
class ClassScheduleActivity : BaseRefreshListActivity<ClassSchedule, ClassSchedulePresenter>(), ClassScheduleView {

    override fun getMainTitle() = R.string.class_schedule_title


    override fun getRecyclerAdapter(): RecyclerArrayAdapter<ClassSchedule> {
        TODO("Not yet implemented")
    }
}