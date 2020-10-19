package com.ftacloud.student.ui.order.list.child

import android.view.ViewGroup
import com.ftacloud.student.frames.components.list.BaseRefreshListFragment
import com.ftacloud.student.frames.entity.Voucher
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

/**
 * 我的代金券列表
 */
class MyVoucherListFragment : BaseRefreshListFragment<Voucher, MyVoucherListPresenter>(), MyVoucherListView {











    override fun getRecyclerAdapter(): RecyclerArrayAdapter<Voucher> {

        val adapter = object : RecyclerArrayAdapter<Voucher>(context) {
            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): VoucherViewHolder {

                val holder = VoucherViewHolder(parent)


                return holder
            }
        }
        adapter.setOnItemClickListener {

        }


        return adapter
    }









}