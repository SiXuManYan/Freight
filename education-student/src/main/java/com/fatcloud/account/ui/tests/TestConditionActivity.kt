package com.fatcloud.account.ui.tests

import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.OnClick
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.ui.tests.question.TestQuestionActivity
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.activity_test.*


/**
 * Created by Wangsw on 2020/9/22 0022 15:57.
 * </br>
 *  测试题条件
 */
class TestConditionActivity : BaseMVPActivity<TestConditionPresenter>(), TestConditionView {


    private var age: ArrayList<String> = ArrayList()
    private var basis: ArrayList<String> = ArrayList()
    private var quizzesId = ""
    private var quizzesOfStudentId = ""


    override fun getLayoutId() = R.layout.activity_test

    override fun initViews() {

        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_ID) || !intent.extras!!.containsKey(Constants.PARAM_STUDENT_ID)) {
            finish()
            return
        }
        quizzesId = intent.extras!!.getString(Constants.PARAM_ID, "")
        quizzesOfStudentId = intent.extras!!.getString(Constants.PARAM_STUDENT_ID, "")

        setMainTitle(getString(R.string.test_detail))
        initSelect()
    }

    private fun initSelect() {
        age.apply {
            add("3岁")
            add("4岁")
            add("5岁")
            add("6岁")
            add("7岁")
            add("8岁")
            add("9岁")
            add("10岁")
            add("11岁")
            add("12岁")
        }

        basis.apply {
            add("无基础")
            add("有点基础")
            add("小学一年级水平")
            add("小学二年级水平")
            add("小学三年级水平")
            add("小学四年级水平")
            add("小学五年级水平")
            add("小学六年级水平")
        }


        age_fl.setMaxSelectCount(1)
        age_fl.adapter = object : TagAdapter<String>(age) {
            override fun getView(parent: FlowLayout?, position: Int, s: String?): View? {
                val tv: TextView = layoutInflater.inflate(R.layout.item_test_age, age_fl, false) as TextView
                tv.text = s
                return tv
            }
        }

        english_fl.setMaxSelectCount(1)
        english_fl.adapter = object : TagAdapter<String>(basis) {
            override fun getView(parent: FlowLayout?, position: Int, s: String?): View? {
                val tv: TextView = layoutInflater.inflate(R.layout.item_test_basis, english_fl, false) as TextView
                tv.text = s
                return tv
            }
        }
    }


    @OnClick(
        R.id.action_tv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.action_tv -> {
                val bundle = Bundle()
                bundle.putString(Constants.PARAM_ID, quizzesId)
                bundle.putString(Constants.PARAM_STUDENT_ID, quizzesOfStudentId)
                startActivity(TestQuestionActivity::class.java, bundle)
            }
            else -> {
            }
        }
    }


}