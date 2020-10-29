package com.ftacloud.student.ui.order.list

import com.blankj.utilcode.util.StringUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.ui.order.list.child.OrderChildFragment
import kotlinx.android.synthetic.main.activity_my_order.*

/**
 * 我的订单
 */
class OrderActivity : BaseMVPActivity<OrderPresenter>(), OrderView {

    companion object {
        internal val TAB_TITLES = arrayListOf(
            StringUtils.getString(R.string.order_all),
            StringUtils.getString(R.string.order_to_be_paid),
            StringUtils.getString(R.string.paid)
        )

        /** 全部 */
        val TYPE_ORDER_ALL = 0

        /** 未支付 */
        val TYPE_ORDER_UNPAID = 1


        /** 已支付 */
        val TYPE_ORDER_PAID = 2



    }


    override fun getLayoutId() = R.layout.activity_my_order

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

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

    override fun onPageSelected(position: Int) {
        tabs_type.currentTab = position
    }

    internal class PagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int) = OrderChildFragment.newInstance(position)

        override fun getCount() = TAB_TITLES.size
    }
}