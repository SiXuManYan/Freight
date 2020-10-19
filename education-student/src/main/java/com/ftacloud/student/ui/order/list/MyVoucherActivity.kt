package com.ftacloud.student.ui.order.list

import com.blankj.utilcode.util.StringUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.ui.order.list.child.MyVoucherListFragment
import kotlinx.android.synthetic.main.activity_my_voucher.*

/**
 * 我的订单
 */
class MyVoucherActivity : BaseMVPActivity<MyVoucherPresenter>(), MyVoucherView {

    companion object {
        internal val TAB_TITLES = arrayListOf(
            StringUtils.getString(R.string.order_all),
            StringUtils.getString(R.string.order_to_be_paid),
            StringUtils.getString(R.string.paying),
            StringUtils.getString(R.string.paid)
        )
    }


    override fun getLayoutId() = R.layout.activity_my_voucher

    override fun initViews() {
        setMainTitle(getString(R.string.order_title))

        pager.adapter = PagerAdapter(supportFragmentManager)
        tabs_type.setViewPager(pager, TAB_TITLES.toTypedArray())
        pager.offscreenPageLimit = TAB_TITLES.size
    }

    override fun onTabSelect(position: Int) {
        pager.currentItem = position
    }

    override fun onTabReselect(position: Int) = Unit

    override fun onPageScrollStateChanged(state: Int) = Unit

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        tabs_type.currentTab = position
    }

    internal class PagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int) = MyVoucherListFragment()

        override fun getCount() = TAB_TITLES.size
    }
}