package com.ftacloud.student.ui.order.list.child

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.frames.components.list.BaseRefreshListFragment
import com.ftacloud.student.frames.entity.Order
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.Constants

/**
 * 我的代金券列表
 */
class OrderChildFragment : BaseRefreshListFragment<Order, OrderChildPresenter>(), OrderChildView {

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

    private var categoryValue: Int? = 0

    override fun initViews(parent: View) {
        super.initViews(parent)
        categoryValue = arguments?.getInt(Constants.PARAM_TYPE)
        initEvent()
    }

    private fun initEvent() {

    }


    override fun getRecyclerAdapter(): RecyclerArrayAdapter<Order> {

        val adapter = object : RecyclerArrayAdapter<Order>(context) {
            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): OrderChildHolder {

                val holder = OrderChildHolder(parent)


                return holder
            }
        }
        adapter.setOnItemClickListener {
            ToastUtils.showShort("订单详情页")
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