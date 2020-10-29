package com.ftacloud.student.ui.course.my.child

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.ftacloud.student.frames.components.list.BaseRefreshListFragment
import com.ftacloud.student.frames.entity.MyCourse
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.Constants

/**
 * Created by Wangsw on 2020/9/27 0027 10:03.
 * </br>
 *  我的课程
 */
class MyCourseChildFragment: BaseRefreshListFragment<MyCourse, MyCourseChildPresenter>(), MyCourseChildView {

    private var categoryValue: Int? = 0

    companion object {

        /**
         * @param categoryValue tab类别，全部时传空
         */
        fun newInstance(categoryValue: Int): MyCourseChildFragment {
            val fragment = MyCourseChildFragment()
            val args = Bundle()
            args.putInt(Constants.PARAM_TYPE, categoryValue)
            fragment.arguments = args
            return fragment
        }

    }


    override fun initViews(parent: View) {
        super.initViews(parent)
        categoryValue = arguments?.getInt(Constants.PARAM_TYPE)
    }



    override fun getRecyclerAdapter(): RecyclerArrayAdapter<MyCourse> {
        val adapter = object : RecyclerArrayAdapter<MyCourse>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<MyCourse> {
                val holder = MyCourseChildHolder(parent)

                return holder
            }

        }
        adapter.setOnItemClickListener {
        }
        return adapter
    }


    override fun onRefresh() {
        super.onRefresh()
        presenter.loadCourseList(this, pageSize, lastItemId, categoryValue)
    }

    override fun onLoadMore() {
        super.onLoadMore()
        presenter.loadCourseList(this, pageSize, lastItemId, categoryValue)
    }




}