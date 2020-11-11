package com.fatcloud.account.ui.course.detail.experience.result

import android.view.View
import butterknife.OnClick
import com.fatcloud.account.ui.main.MainActivity
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseActivity
import com.sugar.library.util.CommonUtils

/**
 * Created by Wangsw on 2020/11/2 0002 9:30.
 * </br>
 *
 */
class ReservationResultActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_reservation_result

    override fun initViews() {

    }

    override fun onBackPressed() = Unit

    @OnClick(
        R.id.back_home_tv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.back_home_tv -> {
                startActivityClearTop(MainActivity::class.java, null)
                finish()
            }
            else -> {
            }
        }
    }

}