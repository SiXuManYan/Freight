package com.ftacloud.student.ui.tests.my

import android.view.ViewGroup
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.list.BaseRefreshListActivity
import com.ftacloud.student.frames.entity.Message
import com.ftacloud.student.frames.entity.MyTest
import com.ftacloud.student.ui.message.MessageHolder
import com.ftacloud.student.ui.message.MessagePresenter
import com.ftacloud.student.ui.message.MessageView
import com.ftacloud.student.ui.tests.my.header.MyTestHeader
import com.ftacloud.student.ui.tests.my.holder.MyTestHolder
import com.ftacloud.student.ui.tests.question.TestQuestionActivity
import com.ftacloud.student.ui.tests.score.TestScoreActivity
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.CommonUtils

/**
 * Created by Wangsw on 2020/9/27 0027 19:13.
 * </br>
 * 我的测评
 *
 */
class MyTestActivity : BaseRefreshListActivity<MyTest, MyTestPresenter>(), MyTestView {

    override fun getMainTitle() = R.string.my_test_title

    override fun initViews() {
        super.initViews()

        val myTestHeader = MyTestHeader(this)
        getAdapter()?.addHeader(myTestHeader)
    }

    override fun getRecyclerAdapter(): RecyclerArrayAdapter<MyTest> {
        val adapter = object : RecyclerArrayAdapter<MyTest>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<MyTest> {

                val holder = MyTestHolder(parent)

                return holder
            }

        }

        adapter.setOnItemClickListener {

            // 评分详情
            startActivity(TestScoreActivity::class.java)

            // 测试题详情
            startActivity(TestQuestionActivity::class.java)


        }
        return adapter
    }
}