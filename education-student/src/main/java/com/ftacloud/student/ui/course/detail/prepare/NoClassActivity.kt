package com.ftacloud.student.ui.course.detail.prepare

import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.StringUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.entity.FormalCourseDetail
import com.ftacloud.student.ui.course.detail.prepare.child.directory.DirectoryFragment
import com.ftacloud.student.ui.course.detail.prepare.child.introduct.IntroductionFragment
import com.ftacloud.student.ui.course.detail.prepare.child.teacher.TeacherFragment
import com.ftacloud.student.ui.order.list.OrderActivity
import kotlinx.android.synthetic.main.activity_no_class2.*

/**
 * Created by Wangsw on 2020/9/29 0029 11:15.
 * </br>
 * 已购买未开课课程详情
 * 首页普通课程(未开课)，侧边栏我的课程(未开课),订单列表已支付(未开课)
 */
class NoClassActivity : BaseMVPActivity<NoClassPresenter>(), NoClassView {


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

    }

    override fun bindDetail(data: FormalCourseDetail) {
        val pagerAdapter = PagerAdapter(supportFragmentManager)
        pagerAdapter.data = data
        pager.adapter = pagerAdapter
        pager.offscreenPageLimit = TAB_TITLES.size
        tabs_type.setViewPager(pager, TAB_TITLES.toTypedArray())
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
            if (position == 0) {
                return DirectoryFragment.newInstance(data!!)
            } else if (position == 1) {
                return IntroductionFragment.newInstance(data!!.productIntroduce)
            } else {
                return TeacherFragment.newInstance(data!!)
            }
        }

        override fun getCount() = OrderActivity.TAB_TITLES.size
    }


}