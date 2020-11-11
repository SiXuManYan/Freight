package com.ftacloud.student.ui.course.my.child

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.baijiayun.groupclassui.InteractiveClassUI
import com.baijiayun.livecore.utils.LPRxUtils
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.ftacloud.student.R
import com.ftacloud.student.common.StudentUtil
import com.ftacloud.student.frames.components.list.BaseRefreshListFragment
import com.ftacloud.student.frames.entity.MyCourse
import com.ftacloud.student.frames.entity.home.CourseState
import com.ftacloud.student.ui.course.detail.live.LiveActivity
import com.ftacloud.student.ui.course.detail.prepare.NoClassActivity
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.Constants
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.item_home_course_common.*
import java.util.concurrent.TimeUnit

/**
 * Created by Wangsw on 2020/9/27 0027 10:03.
 * </br>
 *  我的课程
 */
class MyCourseChildFragment : BaseRefreshListFragment<MyCourse, MyCourseChildPresenter>(), MyCourseChildView {

    private var categoryValue: Int? = 0

    companion object {

        /**
         * @param categoryValue tab类别，全部时传空
         */
        fun newInstance(categoryValue: Int): MyCourseChildFragment {
            val fragment = MyCourseChildFragment()
            val args = Bundle()
            args.putInt(Constants.PARAM_TYPE, categoryValue)
            fragment.arguments = args
            return fragment
        }

    }


    override fun initViews(parent: View) {
        super.initViews(parent)
        categoryValue = arguments?.getInt(Constants.PARAM_TYPE)
        initEvent()
    }

    private fun initEvent() {
        presenter.subsribeEvent(Consumer {
            when (it.code) {
                Constants.EVENT_REFRESH_MY_COURSE -> {
                    onRefresh()
                }
                else -> {
                }
            }
        })
    }

    override fun getRecyclerAdapter(): RecyclerArrayAdapter<MyCourse> {
        val adapter = object : RecyclerArrayAdapter<MyCourse>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<MyCourse> {
                val holder = MyCourseChildHolder(parent)
                getAdapter()?.let {
                    val myCourse = it.allData[holder.adapterPosition]
                    val code: String = myCourse.liveRoomStudentCode
                    StudentUtil.enterLiveRoom(this@MyCourseChildFragment.context!!,holder.enter_ll,code,"测试学生安卓")
                }
                return holder
            }

        }
        adapter.setOnItemClickListener {
            val myCourse = adapter.allData[it]
            if (myCourse.state.contains(CourseState.UNTEACH.name)) {
                // 未开课,
                startActivity(NoClassActivity::class.java)
            } else {
                // 已开课，直接进入直播间
                startActivity(LiveActivity::class.java)
            }
        }
        return adapter
    }


    override fun onRefresh() {
        super.onRefresh()
        presenter.loadCourseList(this, pageSize, lastItemId, categoryValue)
    }

    override fun onLoadMore() {
        super.onLoadMore()
        presenter.loadCourseList(this, pageSize, lastItemId, categoryValue)
    }


}