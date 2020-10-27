package com.ftacloud.student.ui.tests.question

import android.view.ViewGroup
import androidx.recyclerview.widget.OrientationHelper
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.entity.Voucher
import com.ftacloud.student.frames.entity.question.QuestionChild
import com.ftacloud.student.frames.entity.question.QuestionChildType
import com.ftacloud.student.ui.order.list.child.VoucherViewHolder
import com.ftacloud.student.ui.tests.question.holder.FillHolder
import com.ftacloud.student.ui.tests.question.holder.RecordHolder
import com.ftacloud.student.ui.tests.question.holder.SelectHolder
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.Constants
import com.yc.pagerlib.recycler.OnPagerListener
import com.yc.pagerlib.recycler.PagerLayoutManager
import kotlinx.android.synthetic.main.activity_test_question.*

/**
 * Created by Wangsw on 2020/9/27 0027 11:26.
 * </br>
 * 测试题
 */
class TestQuestionActivity : BaseMVPActivity<TestQuestionPresenter>(), TestQuestionView {


    private lateinit var mAdapter: RecyclerArrayAdapter<QuestionChild>

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun getLayoutId() = R.layout.activity_test_question

    override fun initViews() {
        setMainTitle(getString(R.string.test_question_title))
        initRecycleView()
        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_ID)) {
            finish()
            return
        }
        val quizzesId = intent.extras!!.getString(Constants.PARAM_ID, "")
        presenter.loadUserInfo(this, quizzesId)
    }

    private fun initRecycleView() {
        this.mAdapter = getRecyclerAdapter()
        val pagerLayoutManager = PagerLayoutManager(this, OrientationHelper.HORIZONTAL)
        content_rv.adapter = mAdapter
        content_rv.layoutManager = pagerLayoutManager

        pagerLayoutManager.setOnViewPagerListener(object : OnPagerListener {

            override fun onInitComplete() = Unit

            override fun onPageRelease(isNext: Boolean, position: Int) = Unit

            override fun onPageSelected(position: Int, isBottom: Boolean) {

            }
        })


    }


    fun getRecyclerAdapter(): RecyclerArrayAdapter<QuestionChild> {

        val select = 0
        val fill = 1
        val record = 2

        val adapter = object : RecyclerArrayAdapter<QuestionChild>(context) {
            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> {

                return when (viewType) {
                    select -> {
                        SelectHolder(parent)
                    }
                    fill -> {
                        FillHolder(parent)
                    }
                    else -> {
                        RecordHolder(parent)
                    }
                }


            }

            override fun getViewType(position: Int): Int {
                val item = getItem(position)
                val itemType = item.itemType

                return when {
                    itemType.contains(QuestionChildType.SELECT.name) -> {
                        select
                    }
                    itemType.contains(QuestionChildType.FILL_IN_THE_BLANKS.name) -> {
                        fill
                    }
                    else -> {
                        record
                    }
                }
            }

        }

        return adapter
    }


    override fun bindInfo(items: ArrayList<QuestionChild>) {
        if (items.isNotEmpty()) {
            mAdapter.addAll(items)
        }


    }


}