package com.ftacloud.student

import android.view.View
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import butterknife.BindView
import butterknife.OnClick
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.sugar.library.util.CommonUtils

class MainActivity : BaseMVPActivity<MainPresenterLibrary>(), MainView {


    private var tapTime = 0L

    override fun getLayoutId() = R.layout.activity_main

    override fun initViews() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)


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
        R.id.show_iv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.show_iv -> {
                ToastUtils.showShort("头像")
            }
            else -> {
            }
        }
    }


}
