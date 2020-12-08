package com.fatcloud.account.ui.tests.my

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import butterknife.OnClick
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.frames.components.list.BaseRefreshListActivity
import com.fatcloud.account.frames.entity.Message
import com.fatcloud.account.frames.entity.question.my.MyQuestion
import com.fatcloud.account.ui.message.MessageHolder
import com.fatcloud.account.ui.tests.TestConditionActivity
import com.fatcloud.account.ui.tests.my.header.MyTestHeader
import com.fatcloud.account.ui.tests.question.TestQuestionActivity
import com.fatcloud.account.ui.tests.score.TestScoreActivity
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import java.util.ArrayList

/**
 * Created by Wangsw on 2020/9/27 0027 19:13.
 * </br>
 * 我的测评
 *
 */
class MyTestActivity : BaseRefreshListActivity<MyQuestion, MyTestPresenter>(), MyTestView {

    private var header: MyTestHeader? = null

    override fun initViews() {
        super.initViews()

        header = MyTestHeader(this)
        getAdapter()?.addHeader(header)
    }

    override fun getMainTitle() = R.string.my_test

    override fun getRecyclerAdapter(): RecyclerArrayAdapter<MyQuestion> {
        val adapter = object : RecyclerArrayAdapter<MyQuestion>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<MyQuestion> {
                val holder = MyTestHolder(parent)

                return holder
            }
        }

        adapter.setOnItemClickListener {
            val myQuestion = adapter.allData[it]
            if (myQuestion.stateValue == "UNSUBMITTED") {
                startActivity(TestConditionActivity::class.java, Bundle().apply {
                    putString(Constants.PARAM_ID, myQuestion.quizzes?.id)
                    putString(Constants.PARAM_STUDENT_ID, myQuestion.student?.id)
                })
            } else {
                startActivity(TestScoreActivity::class.java, Bundle().apply {
                    putString(Constants.PARAM_STUDENT_ID, myQuestion.student?.id)
                })
            }

        }
        return adapter
    }

    override fun getItemDecoration(): RecyclerView.ItemDecoration? = null

    override fun bindList(list: ArrayList<MyQuestion>, isFirstPage: Boolean, last: Boolean) {
        super.bindList(list, isFirstPage, last)
        if (isFirstPage && list.isNotEmpty()) {
            header!!.setLevel(list[0].stateValue)
            getAdapter()?.notifyItemChanged(0)
        }
    }


}