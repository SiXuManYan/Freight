package com.ftacloud.student.ui.course.detail.prepare.child.introduct

import android.os.Bundle
import android.view.View
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.fragment.BaseFragment
import com.sugar.library.util.Constants
import kotlinx.android.synthetic.main.activity_introduction.*

/**
 * Created by Wangsw on 2020/11/2 0002 20:03.
 * </br>
 * 介绍
 */
class IntroductionFragment : BaseFragment<IntroductionPresenter>(), IntroductionView {

    companion object {

        fun newInstance(content: String): IntroductionFragment {
            val fragment = IntroductionFragment()
            val args = Bundle()
            args.putString(Constants.PARAM_TEXT, content)
            fragment.arguments = args
            return fragment
        }

    }

    override fun loadOnVisible() = Unit

    override fun getLayoutId() = R.layout.activity_introduction

    override fun initViews(parent: View) {

        val content = arguments?.getString(Constants.PARAM_TEXT)
        content_tv.text = content
    }


}