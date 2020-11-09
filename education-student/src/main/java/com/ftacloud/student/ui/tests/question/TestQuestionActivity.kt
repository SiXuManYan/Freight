package com.ftacloud.student.ui.tests.question

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import butterknife.OnClick
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.entity.question.QuestionChild
import com.ftacloud.student.frames.entity.question.QuestionChildType
import com.ftacloud.student.ui.main.MainActivity
import com.ftacloud.student.ui.tests.question.holder.FillHolder
import com.ftacloud.student.ui.tests.question.holder.RecordHolder
import com.ftacloud.student.ui.tests.question.holder.SelectHolder
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.event.RecordUploadEvent
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import com.yc.pagerlib.recycler.OnPagerListener
import com.yc.pagerlib.recycler.PagerLayoutManager
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_test_question.*

/**
 * Created by Wangsw on 2020/9/27 0027 11:26.
 * </br>
 * 测试题
 */
class TestQuestionActivity : BaseMVPActivity<TestQuestionPresenter>(), TestQuestionView {


    private lateinit var mAdapter: RecyclerArrayAdapter<QuestionChild>


    private var index = 0
    private var quizzesId = ""
    private var quizzesOfStudentId = ""

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun getLayoutId() = R.layout.activity_test_question

    override fun initPadLayout() {
        CommonUtils.setStatusBarTransparentWithLightMode(this)
    }

    override fun initViews() {
        setMainTitle(getString(R.string.test_question_title))
        initEvent()
        initRecycleView()
        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_ID) || !intent.extras!!.containsKey(Constants.PARAM_STUDENT_ID)) {
            finish()
            return
        }
        quizzesId = intent.extras!!.getString(Constants.PARAM_ID, "")
        quizzesOfStudentId = intent.extras!!.getString(Constants.PARAM_STUDENT_ID, "")
        presenter.getQuestion(this, quizzesId)
    }

    private fun initEvent() {
        // 图片上传成功
        presenter.subsribeEventEntity<RecordUploadEvent>(Consumer {
            mAdapter.allData[it.position].nativeAnswerRecordServerPath = it.finalUrl
        })
    }

    private fun initRecycleView() {
        this.mAdapter = getRecyclerAdapter()
        val pagerLayoutManager = PagerLayoutManager(this, OrientationHelper.HORIZONTAL)

        content_rv.adapter = mAdapter
        content_rv.layoutManager = pagerLayoutManager
        content_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        pagerLayoutManager.setOnViewPagerListener(object : OnPagerListener {

            override fun onInitComplete() = Unit

            override fun onPageRelease(isNext: Boolean, position: Int) {
            }

            override fun onPageSelected(position: Int, isBottom: Boolean) {

                val findFirstVisibleItemPosition = pagerLayoutManager.findFirstVisibleItemPosition()
                index = findFirstVisibleItemPosition

                if (isBottom) {
                    next_tv.text = getString(R.string.submit)
                } else {
                    next_tv.text = getString(R.string.next_question)
                }

            }
        })


    }


    fun getRecyclerAdapter(): RecyclerArrayAdapter<QuestionChild> {

        val select = 0
        val fill = 1
        val record = 2
        var a = ""

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


    @OnClick(
        R.id.previous_tv,
        R.id.next_tv,
        R.id.back_home_tv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.previous_tv -> {
                content_rv.scrollToPosition(--index)
            }

            R.id.next_tv -> {
                if (index == mAdapter.allData.size - 1) {
                    // 提交练习题
                    presenter.submitQuestion(this, quizzesOfStudentId, mAdapter)
                } else {
                    content_rv.scrollToPosition(++index)
                }
            }
            R.id.back_home_tv -> {
                startActivityClearTop(MainActivity::class.java, null)
                finish()
            }
            else -> {
            }
        }
    }

    override fun submitSuccess() {
        bottom_ll.displayedChild = 1
    }

}