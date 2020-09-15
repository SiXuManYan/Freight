package com.ftacloud.freightuser.ui.home

import android.view.View
import com.ftacloud.freightuser.R
import com.ftacloud.freightuser.frames.components.fragment.BaseFragment

class HomeFragment : BaseFragment<HomePresenter>(),HomeView {


    override fun getLayoutId() = R.layout.fragment_home

    override fun initViews(parent: View) {

    }

    override fun loadOnVisible() {
        TODO("Not yet implemented")
    }





}