package com.ftacloud.student.ui.course.detail.prepare.child.directory

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.fragment.BaseFragment
import com.ftacloud.student.frames.entity.FormalCourseDetail
import com.ftacloud.student.ui.course.detail.prepare.child.introduct.IntroductionFragment
import com.ftacloud.student.ui.order.list.child.OrderChildHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.Constants
import kotlinx.android.synthetic.main.directory.*

/**
 * Created by Wangsw on 2020/11/2 0002 20:02.
 * </br>
 *  目录
 */
class DirectoryFragment : BaseFragment<DirectoryPresenter>(), DirectoryView {


    companion object {

        fun newInstance(content:  FormalCourseDetail): IntroductionFragment {
            val fragment = IntroductionFragment()
            val args = Bundle()
            args.putSerializable(Constants.PARAM_CONTENT, content)
            fragment.arguments = args
            return fragment
        }

    }

    override fun getLayoutId() = R.layout.directory

    override fun loadOnVisible() = Unit

    override fun initViews(parent: View) {
        val module = arguments?.getSerializable(Constants.PARAM_CONTENT) as FormalCourseDetail
        val adapter = getRecyclerAdapter()
        adapter.addAll(module.scheduleOuts)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
    }


     fun getRecyclerAdapter(): RecyclerArrayAdapter<FormalCourseDetail.ScheduleOut> {

        val adapter = object : RecyclerArrayAdapter<FormalCourseDetail.ScheduleOut>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): DirectoryHolder {

                val holder = DirectoryHolder(parent)

                return holder
            }
        }
        return adapter
    }


}