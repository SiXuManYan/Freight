package com.ftacloud.freightuser.ui

import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.freightuser.R
import com.ftacloud.freightuser.frames.components.BaseActivity
import com.ftacloud.freightuser.frames.components.BaseMVPActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseMVPActivity<MainPresenter>(),MainView {

    private var tapTime = 0L

    override fun getLayoutId() = R.layout.activity_main

    override fun initViews() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_my
            )
        )
        navView.setupWithNavController(navController)
    }



    override fun onBackPressed() {
        if (System.currentTimeMillis() - tapTime > 2000) {
            ToastUtils.showShort("再按一次退出")
            tapTime = System.currentTimeMillis()
        } else {
            moveTaskToBack(false);
        }
    }




}