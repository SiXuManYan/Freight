package com.fatcloud.account.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.baijiayun.groupclassui.InteractiveClassUI
import com.baijiayun.livecore.utils.LPRxUtils
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.list.BaseRefreshListFragment
import com.fatcloud.account.frames.entity.Task
import com.fatcloud.account.frames.entity.home.*
import com.fatcloud.account.storage.entity.User
import com.fatcloud.account.ui.course.detail.experience.ExperienceCourseDetailActivity
import com.fatcloud.account.ui.course.detail.experience.ExperienceCourseDetailActivity.Companion.PAY
import com.fatcloud.account.ui.course.detail.experience.ExperienceCourseDetailActivity.Companion.RESERVE
import com.fatcloud.account.ui.course.detail.live.LiveActivity
import com.fatcloud.account.ui.course.detail.prepare.NoClassActivity
import com.fatcloud.account.ui.home.holder.*
import com.fatcloud.account.ui.task.lists.frgm.TaskHolder
import com.fatcloud.account.ui.tests.TestConditionActivity
import com.fatcloud.account.ui.tests.score.TestScoreActivity
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.Constants
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.item_home_course_common.*
import java.util.concurrent.TimeUnit

/**
 * 首页
 */
class HomeFragment : BaseRefreshListFragment<Any, HomePresenter>(), HomeView {

    override fun initViews(parent: View) {
        super.initViews(parent)
        if (DeviceUtils.isTablet()) {
            easyRecyclerView.setLayoutManager(androidx.recyclerview.widget.GridLayoutManager(context, 2))
        }
        loadOnVisible()
        initEvent()

    }

    private fun initEvent() {
        presenter.subsribeEvent(Consumer {
            when (it.code) {
                Constants.EVENT_REFRESH_MY_COURSE,
                Constants.EVENT_BOOKING_EXPERIENCE_SUCCESS -> {
                    onRefresh()
                }
                else -> {
                }
            }
        })
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
                        val commonClassHolder = CommonClassHolder(parent)
                        enterLiveRoom(this@HomeFragment.context!!, commonClassHolder.enter_ll, commonClassHolder)
                        commonClassHolder
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
                    is Course -> {
                        if (item.productType.contains(CourseProductType.EXPERIENCE.name)) {
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
                        HomeConstant.HOME_ORDER        // 订单
                    }
                    is Task -> {
                        HomeViewType.Schedule.ordinal  // 任务
                    }
                    else -> {
                        HomeViewType.Schedule.ordinal  // 任务
                    }
                }

            }
        }

        adapter.setOnItemClickListener {

            val model = adapter.allData[it]

            when (model) {
                is Test -> {
                    testItemClick(model)
                }
                is Course -> {
                    courseItemClick(model)
                }
                is NativeClassSchedule -> {
                    // 不处理
                }
                is HomeOrder -> {
                    orderItemClick(model)
                }
                is Task -> {

                }
            }
        }
        return adapter
    }


    /**
     *   课程点击
     */
    private fun courseItemClick(model: Course) {
        if (model.productType.contains(CourseProductType.EXPERIENCE.name)) {
            // 体验课详情

            // 预约成功未上课
            if (model.state.contains(CourseState.UNTEACH.name)) {
                // 未上课
                startActivity(NoClassActivity::class.java, Bundle().apply {
                    putString(Constants.PARAM_PRODUCT_ID, model.productId)
                })
            }else {
                startActivity(ExperienceCourseDetailActivity::class.java, Bundle().apply {
                    putString(Constants.PARAM_ID, model.id)
                    putInt(Constants.PARAM_TYPE, RESERVE)
                })
            }


        } else {
            // 普通课(区分已上课和)
            when {
                model.state.contains(CourseState.UNTEACH.name) -> {
                    // 未上课
                    startActivity(NoClassActivity::class.java, Bundle().apply {
                        putString(Constants.PARAM_PRODUCT_ID, model.productId)
                    })
                }
                model.state.contains(CourseState.TEACHING.name) -> {
                    // 已经上课
                    startActivity(LiveActivity::class.java)
                }
                model.state.contains(CourseState.TAUGHT.name) -> {
                    ToastUtils.showShort(getString(R.string.course_is_over))
                }
            }
        }
    }

    /**
     * 测试题
     */
    private fun testItemClick(model: Test) {
        if (model.state.contains(TestState.UNSUBMITTED.name)) {
            // 未提交，进入选择基础页
            startActivity(TestConditionActivity::class.java, Bundle().apply {
                putString(Constants.PARAM_ID, model.quizzesId)
                putString(Constants.PARAM_STUDENT_ID, model.quizzesOfStudentId)
            })
        }
        if (model.state.contains(TestState.DONE.name)) {
            // 已经提交,查看评分
            startActivity(TestScoreActivity::class.java, Bundle().apply {
                putString(Constants.PARAM_STUDENT_ID, model.quizzesOfStudentId)
            })
        }
    }


    private fun orderItemClick(model: HomeOrder) {

        val apply = HomeOrderExtra().apply {
            orderId = model.orderId
            productId = model.productId
            productName = model.productName
            productIconImg = model.productIconImg
            productIntroduce = model.productIntroduce
            productMoney = model.productMoney
            payingMoney = model.payingMoney
            quantity = model.quantity
        }
        startActivity(ExperienceCourseDetailActivity::class.java, Bundle().apply {
            putString(Constants.PARAM_ID, model.orderId)
            putInt(Constants.PARAM_TYPE, PAY)
            putSerializable(Constants.PARAM_ORDER, apply)
        })
    }


    @SuppressLint("CheckResult")
    fun enterLiveRoom(context: Context, view: View, commonClassHolder: CommonClassHolder) {

        LPRxUtils.clicks(view)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {

                getAdapter()?.let {
                    val myCourse = it.allData[commonClassHolder.adapterPosition] as Course
                    val code: String = myCourse.liveRoomNo
                    if (code.isBlank()) {
                        ToastUtils.showShort("未找到教室")
                        return@subscribe
                    }
                    InteractiveClassUI.enterRoom(context, code, User.get().name) { msg ->
                        ToastUtils.showShort(msg)
                    }

                }

            }
    }


}
