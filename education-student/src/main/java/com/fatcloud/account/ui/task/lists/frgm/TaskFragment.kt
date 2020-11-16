package com.fatcloud.account.ui.task.lists.frgm

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.DeviceUtils
import com.fatcloud.account.frames.components.list.BaseRefreshListFragment
import com.fatcloud.account.frames.entity.Task
import com.fatcloud.account.ui.task.detail.TaskDetailActivity
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.Constants

/**
 * Created by Wangsw on 2020/9/27 0027 14:24.
 * </br>
 * 课后任务列表
 */
class TaskFragment : BaseRefreshListFragment<Task, TaskPresenter>(), TaskView {


    override fun initViews(parent: View) {
        super.initViews(parent)
        if (DeviceUtils.isTablet()) {
            easyRecyclerView.setLayoutManager(androidx.recyclerview.widget.GridLayoutManager(context, 2))
        }
    }

    override fun getRecyclerAdapter(): RecyclerArrayAdapter<Task> {
        val adapter = object : RecyclerArrayAdapter<Task>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Task> {

                val holder = TaskHolder(parent)



                return holder
            }

        }

        adapter.setOnItemClickListener {

            val task = adapter.allData[it]
            val productId = task.productId

            startActivity(TaskDetailActivity::class.java, Bundle().apply {
                putString(Constants.PARAM_PRODUCT_ID, productId)
            })
        }
        return adapter
    }
}