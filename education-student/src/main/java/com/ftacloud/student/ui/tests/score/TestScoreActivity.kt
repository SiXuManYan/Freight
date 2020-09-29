package com.ftacloud.student.ui.tests.score

import android.view.ViewGroup
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.list.BaseRefreshListActivity
import com.ftacloud.student.frames.entity.TestScore
import com.ftacloud.student.ui.tests.score.header.TestScoreHeader
import com.ftacloud.student.ui.tests.score.holder.TestScoreHolder
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

/**
 * Created by Wangsw on 2020/9/27 0027 20:09.
 * </br>
 *  评分详情
 */
class TestScoreActivity  : BaseRefreshListActivity<TestScore, TestScorePresenter>(), TestScoreView {

    override fun getMainTitle() = R.string.score_title

    override fun initViews() {
        super.initViews()

        val myTestHeader = TestScoreHeader(this)
        getAdapter()?.addHeader(myTestHeader)
    }

    override fun getRecyclerAdapter(): RecyclerArrayAdapter<TestScore> {
        val adapter = object : RecyclerArrayAdapter<TestScore>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<TestScore> {

                val holder = TestScoreHolder(parent)

                return holder
            }

        }

        adapter.setOnItemClickListener {




        }
        return adapter
    }
}