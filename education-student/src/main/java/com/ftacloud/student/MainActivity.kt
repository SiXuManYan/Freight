package com.ftacloud.student

import android.view.View
import android.widget.RelativeLayout
import androidx.drawerlayout.widget.DrawerLayout
import butterknife.OnClick
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.ui.settings.SettingActivity
import com.sugar.library.util.CommonUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : BaseMVPActivity<MainPresenter>(), MainView {


    private var tapTime = 0L

    override fun getLayoutId() = R.layout.activity_main

    override fun initViews() {

        val headerView = nav_view.getHeaderView(0)
        headerView.findViewById<RelativeLayout>(R.id.menu_my_setting) .setOnClickListener {
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
