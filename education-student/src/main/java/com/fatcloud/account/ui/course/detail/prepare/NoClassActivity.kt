package com.fatcloud.account.ui.course.detail.prepare

import android.annotation.SuppressLint
import android.content.Context
import android.os.SystemClock
import android.view.View
import androidx.fragment.app.Fragment
import butterknife.OnClick
import com.baijiayun.groupclassui.InteractiveClassUI
import com.baijiayun.livecore.utils.LPRxUtils
import com.blankj.utilcode.constant.TimeConstants
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.frames.entity.FormalCourseDetail
import com.fatcloud.account.frames.entity.home.Course
import com.fatcloud.account.frames.entity.home.CourseState
import com.fatcloud.account.frames.event.NoClassDataEvent
import com.fatcloud.account.storage.entity.User
import com.fatcloud.account.ui.course.detail.prepare.child.directory.DirectoryFragment
import com.fatcloud.account.ui.course.detail.prepare.child.introduct.IntroductionFragment
import com.fatcloud.account.ui.course.detail.prepare.child.teacher.TeacherFragment
import com.fatcloud.account.ui.home.holder.CommonClassHolder
import com.fatcloud.account.ui.order.list.OrderActivity
import com.sugar.library.event.RxBus
import com.sugar.library.ui.widget.countdown.CountDownTextView
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import com.sugar.library.util.TimeUtil
import kotlinx.android.synthetic.main.activity_no_class2.*
import java.util.concurrent.TimeUnit

/**
 * Created by Wangsw on 2020/9/29 0029 11:15.
 * </br>
 * 已购买未开课课程详情
 * 首页普通课程(未开课)，侧边栏我的课程(未开课),订单列表已支付(未开课)
 */
class NoClassActivity : BaseMVPActivity<NoClassPresenter>(), NoClassView {

    private var mLiveRoomNo = ""

    companion object {
        internal val TAB_TITLES = arrayListOf(
            StringUtils.getString(R.string.course_directory),
            StringUtils.getString(R.string.course_introduction),
            StringUtils.getString(R.string.teacher_introduction)
        )
    }

    override fun getLayoutId() = R.layout.activity_no_class2

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initViews() {
        val pagerAdapter = PagerAdapter(supportFragmentManager)
        pager.adapter = pagerAdapter
        pager.offscreenPageLimit = TAB_TITLES.size
        tabs_type.setViewPager(pager, TAB_TITLES.toTypedArray())
        initExtra()
    }

    private fun initExtra() {
        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_PRODUCT_ID)) {
            finish()
            return
        }
        val productId = intent.extras!!.getString(Constants.PARAM_PRODUCT_ID)!!
        presenter.loadDetail(this, productId)
    }

    override fun bindDetail(data: FormalCourseDetail) {
        RxBus.post(NoClassDataEvent(data))
        mLiveRoomNo = data.liveRoomNo

        if (data.scheduleOuts.isNotEmpty()) {

            val scheduleOut = data.scheduleOuts[0]

            when (scheduleOut.scheduleState) {
                CourseState.UNTEACH.name -> {
                    bottom_sw.displayedChild = 0
                    val timeStrToSecond = TimeUtil.timeStrToSecond(scheduleOut.studyDatetime)
                    initCountDown(timeStrToSecond)
                }
                CourseState.TEACHING.name -> {
                    bottom_sw.displayedChild = 1
                }

                else -> {
                }
            }
        }
    }

    private fun initCountDown(endTime: Long) {
        when {
            endTime <= 0 -> {
                count_down.text = "未开课"
            }
            endTime > TimeConstants.DAY -> {
                count_down.text = "开课时间 " + TimeUtil.getDateTimeFromMillisecond(endTime)
            }
            else -> {
                count_down.apply {
                    cancel()
                    setTimeInFuture(SystemClock.elapsedRealtime() + endTime)
                    setAutoDisplayText(true)
                    setTimeFormat(CountDownTextView.TIME_SHOW_D_H_M_S)
                    start()
                    addCountDownCallback(object : CountDownTextView.CountDownCallback {

                        override fun onTick(countDownTextView: CountDownTextView?, millisUntilFinished: Long) = Unit

                        override fun onFinish(countDownTextView: CountDownTextView?) {
                            bottom_sw.displayedChild = 1
                        }
                    })
                }
            }
        }

    }

    @OnClick(
        R.id.enter_tv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.enter_tv -> {

            }
            else -> {
            }
        }
    }


    override fun onTabSelect(position: Int) {
        pager.currentItem = position
    }

    override fun onTabReselect(position: Int) = Unit

    override fun onPageScrollStateChanged(state: Int) = Unit

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

    override fun onPageSelected(position: Int) {
        tabs_type.currentTab = position
    }

    internal class PagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

        var data: FormalCourseDetail? = null

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    DirectoryFragment()
                }
                1 -> {
                    IntroductionFragment()
                }
                else -> {
                    TeacherFragment()
                }
            }
        }

        override fun getCount() = OrderActivity.TAB_TITLES.size
    }


    @SuppressLint("CheckResult")
    fun enterLiveRoom(context: Context, view: View) {

        LPRxUtils.clicks(view)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {


                val code: String = mLiveRoomNo
                if (code.isBlank()) {
                    ToastUtils.showShort(R.string.classroom_not_exit)
                    return@subscribe
                }
                var name = User.get().name
                if (name.isNullOrBlank()) {
                    name = "学生1"
                }

                InteractiveClassUI.enterRoom(context, code, name) { msg ->
                    ToastUtils.showShort(msg)
                }
            }
    }

}