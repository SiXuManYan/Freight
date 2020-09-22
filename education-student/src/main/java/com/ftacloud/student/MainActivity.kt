package com.ftacloud.student

import android.view.View
import android.widget.RelativeLayout
import butterknife.OnClick
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.ui.settings.SettingActivity
import com.ftacloud.student.ui.tests.TestConditionActivity
import com.sugar.library.util.CommonUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVPActivity<MainPresenter>(), MainView {


    private var tapTime = 0L

    override fun getLayoutId() = R.layout.activity_main

    override fun initViews() {


        val headerView = nav_view.getHeaderView(0)
        headerView.findViewById<RelativeLayout>(R.id.menu_my_setting) .setOnClickListener {
            startActivity(SettingActivity::class.java)
        }

        headerView.findViewById<RelativeLayout>(R.id.menu_my_evaluation) .setOnClickListener {
            startActivity(TestConditionActivity::class.java)
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
        R.id.my_iv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.my_iv -> {
                drawer_layout.open()
            }
            else -> {
            }
        }
    }


}
