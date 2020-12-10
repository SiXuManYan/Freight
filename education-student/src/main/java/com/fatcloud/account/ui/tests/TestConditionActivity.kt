package com.fatcloud.account.ui.tests

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.OnClick
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.storage.entity.User
import com.fatcloud.account.ui.tests.question.TestQuestionActivity
import com.fatcloud.account.ui.user.UserActivity
import com.sugar.library.ui.widget.dialog.AlertDialog
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
            add(getString(R.string.age_3))
            add(getString(R.string.age_4))
            add(getString(R.string.age_5))
            add(getString(R.string.age_6))
            add(getString(R.string.age_7))
            add(getString(R.string.age_8))
            add(getString(R.string.age_9))
            add(getString(R.string.age_10))
            add(getString(R.string.age_11))
            add(getString(R.string.age_12))

        }

        basis.apply {
            add(getString(R.string.basis_none))
            add(getString(R.string.basis_a_little))
            add(getString(R.string.basis_one))
            add(getString(R.string.basis_two))
            add(getString(R.string.basis_three))
            add(getString(R.string.basis_four))
            add(getString(R.string.basis_five))
            add(getString(R.string.basis_six))
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
                handleNext()
            }
            else -> {
            }
        }
    }

    private fun handleNext() {

        if (User.get().headImg.isBlank()) {

            AlertDialog.Builder(this)
                .setTitle(R.string.hint)
                .setMessage(R.string.hint_empty_user)
                .setPositiveButton(R.string.confirm, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, which ->
                    startActivity(UserActivity::class.java)
                    dialog.dismiss()
                })
                .create()
                .show()
            return
        }

        val bundle = Bundle()
        bundle.putString(Constants.PARAM_ID, quizzesId)
        bundle.putString(Constants.PARAM_STUDENT_ID, quizzesOfStudentId)
        startActivity(TestQuestionActivity::class.java, bundle)
    }


}