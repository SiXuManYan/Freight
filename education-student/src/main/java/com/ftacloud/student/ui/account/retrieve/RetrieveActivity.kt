package com.ftacloud.student.ui.account.retrieve

import android.view.View
import butterknife.OnClick
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.sugar.library.util.ProductUtils
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Created by Wangsw on 2020/9/21 0021 9:37.
 * </br>
 *
 */
class RetrieveActivity : BaseMVPActivity<RetrievePresenter>(), RetrieveView {

    override fun getLayoutId() = R.layout.activity_login_forget


    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initViews() {


    }


    @OnClick(
        R.id.verify_code_tv,
        R.id.submit_tv
    )
    fun onClick(view: View) {
        ProductUtils.handleDoubleClick(view)
        when (view.id) {

            R.id.verify_code_tv -> {
                ToastUtils.showShort("获取验证码")
            }

            R.id.submit_tv -> {
                val phoneValue = phone_aet.text.toString().trim()
                val verifyValue = verify_code_aet.text.toString().trim()
                val passwordValue = password_aet.text.toString().trim()
                val passwordAgainValue = password_again_aet.text.toString().trim()
                presenter.retrieve(phoneValue, verifyValue, passwordValue, passwordAgainValue)

            }
        }
    }


    override fun retrieveSuccess() {
        ToastUtils.showShort("密码找回成功")
        finish()
    }


}