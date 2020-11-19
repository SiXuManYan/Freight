package com.fatcloud.account.ui.course.detail.prepare.child.directory

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.fragment.BaseFragment
import com.fatcloud.account.frames.entity.FormalCourseDetail
import com.fatcloud.account.frames.event.NoClassDataEvent
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.directory.*

/**
 * Created by Wangsw on 2020/11/2 0002 20:02.
 * </br>
 *  目录
 */
class DirectoryFragment : BaseFragment<DirectoryPresenter>(), DirectoryView {


    private lateinit var recyclerAdapter: RecyclerArrayAdapter<FormalCourseDetail.ScheduleOut>



    override fun getLayoutId() = R.layout.directory

    override fun loadOnVisible() = Unit

    override fun initViews(parent: View) {
        this@DirectoryFragment. recyclerAdapter = getRecyclerAdapter()
        val adapter = recyclerAdapter
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        initEvent()
    }

    private fun initEvent() {
        presenter.subsribeEventEntity(Consumer<NoClassDataEvent> {
            recyclerAdapter.addAll(it.data.scheduleOuts)
        })
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