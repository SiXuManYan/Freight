package com.ftacloud.student.ui.task.reserve

import android.view.ViewGroup
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.list.BaseRefreshListActivity
import com.ftacloud.student.frames.entity.ReserveList
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

/**
 * Created by Wangsw on 2020/11/2 0002 11:52.
 * </br>
 *
 */
class ReserveListActivity : BaseRefreshListActivity<ReserveList, ReserveListPresenter>(), ReserveListView {

    override fun getMainTitle() = R.string.title_buddy

    override fun getRecyclerAdapter(): RecyclerArrayAdapter<ReserveList> {
        val adapter = object : RecyclerArrayAdapter<ReserveList>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<ReserveList> {

                val holder = ReserveHolder(parent)

                return holder
            }

        }
        return adapter

    }


}