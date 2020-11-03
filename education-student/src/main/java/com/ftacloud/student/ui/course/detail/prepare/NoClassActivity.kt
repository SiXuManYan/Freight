package com.ftacloud.student.ui.course.detail.prepare

import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.entity.FormalCourseDetail

/**
 * Created by Wangsw on 2020/9/29 0029 11:15.
 * </br>
 * 已购买未开课课程详情
 * 首页普通课程(未开课)，侧边栏我的课程(未开课),订单列表已支付(未开课)
 */
class NoClassActivity : BaseMVPActivity<NoClassPresenter>(), NoClassView {


    override fun getLayoutId() = R.layout.activity_no_class

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initViews() {

    }

    override fun bindDetail(data: FormalCourseDetail) {

    }


}