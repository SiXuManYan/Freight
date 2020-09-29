package com.ftacloud.student

import android.view.View
import android.widget.RelativeLayout
import butterknife.OnClick
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.ui.course.my.MyCourseActivity
import com.ftacloud.student.ui.course.schedule.ClassScheduleActivity
import com.ftacloud.student.ui.message.MessageActivity
import com.ftacloud.student.ui.settings.SettingActivity
import com.ftacloud.student.ui.task.TaskActivity
import com.ftacloud.student.ui.tests.TestConditionActivity
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVPActivity<MainPresenter>(), MainView {


    private var tapTime = 0L

    override fun getLayoutId() = R.layout.activity_main

    override fun initViews() {
        initEvent()
        initHeaderView()
        presenter.loadUserInfo(this)
    }

    private fun initEvent() {
        presenter.subsribeEvent(Consumer {
            when (it.code) {
                Constants.EVENT_LOGOUT -> {
                    finish()
                }
                else -> {
                }
            }
        })
    }

    private fun initHeaderView() {

        val headerView = nav_view.getHeaderView(0)

        headerView.findViewById<RelativeLayout>(R.id.menu_my_course).setOnClickListener {
            startActivity(MyCourseActivity::class.java)
        }

        headerView.findViewById<RelativeLayout>(R.id.menu_class_schedule).setOnClickListener {
            startActivity(ClassScheduleActivity::class.java)
        }

        headerView.findViewById<RelativeLayout>(R.id.menu_task).setOnClickListener {
            startActivity(TaskActivity::class.java)
        }

        headerView.findViewById<RelativeLayout>(R.id.menu_my_evaluation).setOnClickListener {
            startActivity(TestConditionActivity::class.java)
        }

        headerView.findViewById<RelativeLayout>(R.id.menu_my_order).setOnClickListener {
            ToastUtils.showShort("我的订单")

        }
        headerView.findViewById<RelativeLayout>(R.id.menu_my_customer_service).setOnClickListener {
            ToastUtils.showShort("我的客服")

        }

        headerView.findViewById<RelativeLayout>(R.id.menu_my_setting).setOnClickListener {
            startActivity(SettingActivity::class.java)
        }


    }


    override fun onBackPressed() {
        if (System.currentTimeMillis() - tapTime > 2000) {
            ToastUtils.showShort("再按一次退出")
            tapTime = System.currentTimeMillis()
        } else {
            // 保留应用状态
            moveTaskToBack(false);
        }
    }


    @OnClick(
        R.id.my_iv,
        R.id.message_iv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.my_iv -> {
                drawer_layout.open()
            }
            R.id.message_iv -> {
                startActivity(MessageActivity::class.java)
            }
            else -> {
            }
        }
    }


}
