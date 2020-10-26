package com.ftacloud.student.ui.tests.question

import android.view.ViewGroup
import androidx.recyclerview.widget.OrientationHelper
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.entity.Voucher
import com.ftacloud.student.frames.entity.question.QuestionChild
import com.ftacloud.student.ui.order.list.child.VoucherViewHolder
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

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun getLayoutId() = R.layout.activity_test_question

    override fun initViews() {

        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_ID)) {
            finish()
            return
        }
        val quizzesId = intent.extras!!.getString(Constants.PARAM_ID, "")
        initRecycleView()


    }

    private fun initRecycleView() {
        val pagerLayoutManager = PagerLayoutManager(this, OrientationHelper.HORIZONTAL)
        content_rv.layoutManager = pagerLayoutManager
        content_rv.adapter = getRecyclerAdapter()

        pagerLayoutManager.setOnViewPagerListener(object : OnPagerListener {

            override fun onInitComplete() = Unit

            override fun onPageRelease(isNext: Boolean, position: Int) = Unit

            override fun onPageSelected(position: Int, isBottom: Boolean) {

            }
        })


    }


    fun getRecyclerAdapter(): RecyclerArrayAdapter<QuestionChild> {

        val adapter = object : RecyclerArrayAdapter<QuestionChild>(context) {
            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): VoucherViewHolder {

                val holder = VoucherViewHolder(parent)


                return holder
            }
        }

        return adapter
    }


    override fun bindInfo(items: ArrayList<QuestionChild>) {


    }


}