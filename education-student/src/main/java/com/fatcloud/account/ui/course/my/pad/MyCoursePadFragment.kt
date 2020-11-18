package com.fatcloud.account.ui.course.my.pad

import android.view.View
import android.widget.CompoundButton
import butterknife.OnCheckedChanged
import com.blankj.utilcode.util.StringUtils
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.fragment.BaseFragment
import com.fatcloud.account.ui.course.my.MyCoursePresenter
import com.fatcloud.account.ui.course.my.child.MyCourseChildFragment
import kotlinx.android.synthetic.main.activity_my_course_pad.*

/**
 * Created by Wangsw on 2020/11/17 0017 17:00.
 * </br>
 *
 */
class MyCoursePadFragment : BaseFragment<MyCoursePadPresenter>(), MyCoursePadView {


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


    override fun getLayoutId() = R.layout.activity_my_course_pad


    override fun initViews(parent: View) {
        pager.adapter = PagerAdapter(childFragmentManager)
        pager.offscreenPageLimit = TAB_TITLES.size
        pager.addOnPageChangeListener(this)
    }

    override fun loadOnVisible() = Unit


    override fun onPageScrollStateChanged(state: Int) = Unit

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

    override fun onPageSelected(position: Int) {
        if (position == 0) {
            un_teach_rb.isChecked = true
        } else {
            already_teach_rb.isChecked = true
        }
    }


    internal class PagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int) = MyCourseChildFragment.newInstance(position)

        override fun getCount() = TAB_TITLES.size
    }


    @OnCheckedChanged(R.id.un_teach_rb, R.id.already_teach_rb)
    fun checkedChange(buttonView: CompoundButton, isChanged: Boolean) {
        if (!isChanged) {
            return
        }
        when (buttonView.id) {
            R.id.un_teach_rb -> {
                pager.currentItem = 0
            }
            R.id.already_teach_rb -> {
                pager.currentItem = 1
            }
            else -> {

            }


        }


    }


}
