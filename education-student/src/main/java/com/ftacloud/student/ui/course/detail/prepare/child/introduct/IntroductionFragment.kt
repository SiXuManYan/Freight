package com.ftacloud.student.ui.course.detail.prepare.child.introduct

import android.view.View
import com.blankj.utilcode.util.DeviceUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.fragment.BaseFragment
import com.ftacloud.student.frames.event.NoClassDataEvent
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_introduction.*

/**
 * Created by Wangsw on 2020/11/2 0002 20:03.
 * </br>
 * 介绍
 */
class IntroductionFragment : BaseFragment<IntroductionPresenter>(), IntroductionView {

    override fun loadOnVisible() = Unit

    override fun getLayoutId() = R.layout.activity_introduction

    override fun initViews(parent: View) {

        if (DeviceUtils.isTablet()) {
            content_tv.textSize = 8f
        }


        presenter.subsribeEventEntity(Consumer<NoClassDataEvent> {
            content_tv.text = it.data.productIntroduce
        })

    }


}