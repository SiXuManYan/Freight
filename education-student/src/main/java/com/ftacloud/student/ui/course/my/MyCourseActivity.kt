package com.ftacloud.student.ui.course.my

import android.view.ViewGroup
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.list.BaseRefreshListActivity
import com.ftacloud.student.frames.entity.MyCourse
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

/**
 * Created by Wangsw on 2020/9/27 0027 10:03.
 * </br>
 *  我的课程 - > 课程详情
 */
class MyCourseActivity: BaseRefreshListActivity<MyCourse, MyCoursePresenter>(), MyCourseView {

    override fun getMainTitle() = R.string.my_schedule_title



    override fun getRecyclerAdapter(): RecyclerArrayAdapter<MyCourse> {
        val adapter = object : RecyclerArrayAdapter<MyCourse>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<MyCourse> {
                val holder = MyCourseHolder(parent)

                return holder
            }

        }
        adapter.setOnItemClickListener {

        }
        return adapter
    }


}