package com.ftacloud.student.ui.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.SnapHelper
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.list.BaseRefreshListFragment
import com.ftacloud.student.frames.entity.Task
import com.ftacloud.student.frames.entity.home.*
import com.ftacloud.student.ui.course.detail.live.LiveActivity
import com.ftacloud.student.ui.course.detail.prepare.NoClassActivity
import com.ftacloud.student.ui.home.holder.*
import com.ftacloud.student.ui.task.TaskHolder
import com.ftacloud.student.ui.tests.TestConditionActivity
import com.ftacloud.student.ui.tests.score.TestScoreActivity
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.Constants

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

                    is Test -> {
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

            val model = adapter.allData[it]
            when (model) {
                is Test -> {
                    if (model.state.contains(TestState.UNSUBMITTED.name)) {
                        // 未提交，进入选择基础页
                        startActivity(TestConditionActivity::class.java, Bundle().apply {
                            putString(Constants.PARAM_ID, model.quizzesId)
                            putString(Constants.PARAM_STUDENT_ID, model.quizzesOfStudentId)
                        })
                    }
                    if (model.state.contains(TestState.DONE.name)) {
                        // 已经提交
                        startActivity(TestScoreActivity::class.java)
                    }
                }
                is Schedule -> {
                    if (model.productType.contains(ScheduleProductType.EXPERIENCE.name)) {
                        // 体验课详情
                    } else {
                        // 普通课(区分已上课和)

                        when {
                            model.state.contains(ScheduleState.UNTEACH.name) -> {
                                // 未上课
                                startActivity(NoClassActivity::class.java)
                            }
                            model.state.contains(ScheduleState.TEACHING.name) -> {
                                // 已经上课
                                startActivity(LiveActivity::class.java)
                            }
                            model.state.contains(ScheduleState.TAUGHT.name) -> {
                                ToastUtils.showShort(getString(R.string.course_is_over))
                            }
                        }
                    }
                }
                is NativeClassSchedule -> {
                    // 不处理
                }
                is HomeOrder -> {

                }
                is Task -> {

                }

            }


        }
        return adapter
    }


}
