package com.fatcloud.account.ui.order.list.child

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.DeviceUtils
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.list.BaseRefreshListFragment
import com.fatcloud.account.frames.entity.Order
import com.fatcloud.account.ui.course.detail.experience.ExperienceCourseDetailActivity
import com.fatcloud.account.ui.course.detail.experience.ExperienceCourseDetailActivity.Companion.PAY
import com.fatcloud.account.ui.course.detail.prepare.NoClassActivity
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.Constants

/**
 * 我的代金券列表
 */
class OrderChildFragment : BaseRefreshListFragment<Order, OrderChildPresenter>(), OrderChildView {

    private var categoryValue: Int? = 0

    companion object {

        /**
         * @param categoryValue tab类别，全部时传空
         */
        fun newInstance(categoryValue: Int): OrderChildFragment {
            val fragment = OrderChildFragment()
            val args = Bundle()
            args.putInt(Constants.PARAM_TYPE, categoryValue)
            fragment.arguments = args
            return fragment
        }

    }

    override fun initViews(parent: View) {
        super.initViews(parent)
        if (DeviceUtils.isTablet()) {
            easyRecyclerView.setLayoutManager(androidx.recyclerview.widget.GridLayoutManager(context, 2))
            parent_container.setBackgroundColor(ColorUtils.getColor(R.color.student_yellow))
            swipeLayout.setBackgroundColor(ColorUtils.getColor(R.color.student_yellow))
        }
        categoryValue = arguments?.getInt(Constants.PARAM_TYPE)
    }


    override fun getRecyclerAdapter(): RecyclerArrayAdapter<Order> {

        val adapter = object : RecyclerArrayAdapter<Order>(context) {
            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): OrderChildHolder {

                val holder = OrderChildHolder(parent)


                return holder
            }
        }
        adapter.setOnItemClickListener {
            val order = adapter.allData[it]
            if (order.state.contains(Order.OrderState.UNPAID.name)) {
                // 未支付
                startActivity(ExperienceCourseDetailActivity::class.java, Bundle().apply {
                    putString(Constants.PARAM_ID, order.courseIntd)
                    putInt(Constants.PARAM_TYPE, PAY)
                })
            }

            if (order.state.contains(Order.OrderState.PAID.name)) {

            }

        }
        return adapter
    }


    override fun onRefresh() {
        super.onRefresh()
        presenter.loadOrderList(this, pageSize, lastItemId, categoryValue)
    }

    override fun onLoadMore() {
        super.onLoadMore()
        presenter.loadOrderList(this, pageSize, lastItemId, categoryValue)
    }


}