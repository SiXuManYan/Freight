package com.ftacloud.student.ui.tests

import android.view.View
import android.widget.TextView
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
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


    override fun getLayoutId() = R.layout.activity_test

    override fun initViews() {

        setMainTitle("测试详情")

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


}