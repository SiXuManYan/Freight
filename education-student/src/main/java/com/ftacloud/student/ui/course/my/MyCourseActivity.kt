package com.ftacloud.student.ui.course.my

import com.blankj.utilcode.util.StringUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.ui.course.my.child.MyCourseChildFragment
import kotlinx.android.synthetic.main.activity_my_course.*

/**
 * Created by Wangsw on 2020/10/29 0029 13:35.
 * </br>
 *  我的课程
 */
class MyCourseActivity : BaseMVPActivity<MyCoursePresenter>(), MyCourseView {


    companion object {
        internal val TAB_TITLES = arrayListOf(
            StringUtils.getString(R.string.course_un_teach),
            StringUtils.getString(R.string.course_already_teach)
        )

        /** 未开课  */
        val COURSE_UN_TEACH = 0

        /** 已开课 */
        val COURSE_ALREADY_TEACH = 1
    }


    override fun getLayoutId() = R.layout.activity_my_course


    override fun initViews() {
        setMainTitle(getString(R.string.my_course))

        pager.adapter = PagerAdapter(supportFragmentManager)
        tabs_type.setViewPager(pager, TAB_TITLES.toTypedArray())
        pager.offscreenPageLimit = TAB_TITLES.size
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

        override fun getItem(position: Int) = MyCourseChildFragment.newInstance(position)

        override fun getCount() = TAB_TITLES.size
    }

}