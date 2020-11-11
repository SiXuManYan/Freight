package com.ftacloud.student.ui.order.pay.result

import android.content.DialogInterface
import android.view.View
import butterknife.OnClick
import com.ftacloud.student.ui.main.MainActivity
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseActivity
import com.ftacloud.student.ui.order.list.OrderActivity
import com.sugar.library.ui.view.dialog.AlertDialog
import com.sugar.library.util.CommonUtils

/**
 * Created by Wangsw on 2020/6/16 0016 20:56.
 * </br>
 * 支付结果页
 */
class CloudPayResultActivity : BaseActivity() {



    override fun getLayoutId(): Int = R.layout.activity_pay_result


    override fun initViews() {
        setMainTitle(getString(R.string.pay_detail))
    }


    override fun onBackPressed() {
            AlertDialog.Builder(this)
                .setMessage(R.string.hint)
                .setCancelable(false)
                .setPositiveButton(R.string.back_home, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, which ->

                    startActivityClearTop(OrderActivity::class.java, null)
                    dialog.dismiss()
                    super.onBackPressed()
                })
                .setNegativeButton(R.string.view_order, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, which ->
                    startActivityClearTop(MainActivity::class.java,null)
                    dialog.dismiss()
                    super.onBackPressed()
                })
                .create()
                .show()

    }


    @OnClick(
        R.id.common_next_tv,
        R.id.back_home_tv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.common_next_tv -> {
                startActivityClearTop(OrderActivity::class.java, null)
                finish()
            }
            R.id.back_home_tv->{
                startActivityClearTop(MainActivity::class.java,null)
                finish()
            }

            else -> {
            }
        }
    }


}