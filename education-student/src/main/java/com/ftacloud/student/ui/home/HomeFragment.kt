package com.ftacloud.student.ui.home

import android.view.View
import android.view.ViewGroup
import com.ftacloud.student.frames.components.list.BaseRefreshListFragment
import com.ftacloud.student.frames.entity.Task
import com.ftacloud.student.frames.entity.home.*
import com.ftacloud.student.ui.home.holder.*
import com.ftacloud.student.ui.task.TaskHolder
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

/**
 * 首页
 */
class HomeFragment : BaseRefreshListFragment<Any, HomePresenter>(), HomeView {

    override fun initViews(parent: View) {
        super.initViews(parent)
        loadOnVisible()
    }

    override fun getRecyclerAdapter(): RecyclerArrayAdapter<Any> {

        val adapter = object : RecyclerArrayAdapter<Any>(context) {
            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> {


                return when (viewType) {
                    HomeConstant.TEST -> {
                        TestHolder(parent)
                    }
                    HomeConstant.EXPERIENCE_CLASS -> {
                        ExperienceClassHolder(parent)
                    }
                    HomeConstant.COMMON_CLASS -> {
                        CommonClassHolder(parent)
                    }
                    HomeConstant.CLASS_SCHEDULE -> {
                        ScheduleHolder(parent)
                    }
                    HomeConstant.HOME_ORDER -> {
                        HomeOrderHolder(parent)
                    }
                    else -> {
                        TaskHolder(parent).apply {
                            showTitle = true
                        }
                    }
                }

            }

            override fun getViewType(position: Int): Int {
                val item = getItem(position)

                return when (item) {

                    is Quizzes -> {
                        // 基础测验
                        HomeConstant.TEST
                    }
                    is Schedule -> {

                        if (item.productType.contains(ScheduleProductType.EXPERIENCE.name)) {
                            // 体验课
                            HomeConstant.EXPERIENCE_CLASS
                        } else {
                            // 普通课
                            HomeConstant.COMMON_CLASS
                        }
                    }
                    is NativeClassSchedule -> {
                        HomeConstant.CLASS_SCHEDULE    // 课程表
                    }
                    is HomeOrder -> {
                        HomeConstant.HOME_ORDER         // 订单
                    }
                    is Task -> {
                        HomeViewType.Schedule.ordinal     // 任务
                    }
                    else -> {
                        HomeViewType.Schedule.ordinal    // 任务
                    }
                }

            }
        }

        adapter.setOnItemClickListener {


        }
        return adapter
    }


}
