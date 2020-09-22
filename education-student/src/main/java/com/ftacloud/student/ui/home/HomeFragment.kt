package com.ftacloud.student.ui.home

import android.view.View
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.fragment.BaseFragment

class HomeFragment : BaseFragment<HomePresenter>(),HomeView {

    override fun getLayoutId() = R.layout.fragment_home

    override fun initViews(parent: View) {

    }

    override fun loadOnVisible() {

    }


}
