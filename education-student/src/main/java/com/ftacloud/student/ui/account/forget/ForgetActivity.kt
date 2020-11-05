package com.ftacloud.student.ui.account.forget

import android.view.View
import butterknife.OnClick
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.R
import com.ftacloud.student.common.StudentUtil
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.ProductUtils
import kotlinx.android.synthetic.main.activity_login_forget.*

/**
 * Created by Wangsw on 2020/9/21 0021 9:37.
 * </br>
 *  忘记密码(设置密码)
 */
class ForgetActivity : BaseMVPActivity<ForgetPresenter>(), ForgetView {


    /**
     * 是否为密文
     */
    private var isCipherText = true


    private var againIsCipherText = true

    override fun getLayoutId() = R.layout.activity_login_forget


    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initPadLayout() {
        CommonUtils.setStatusBarTransparent(this)
        BarUtils.setNavBarVisibility(this, false)
    }

    override fun initViews() {


    }


    private fun changeDisplayMethod() {

        if (isCipherText) {
            // 切换至明文
            StudentUtil.editOpen(password_aet, password_rule_iv)
        } else {
            // 切换至密文
            StudentUtil.editDismiss(password_aet, password_rule_iv)
        }
        isCipherText = !isCipherText
    }

    private fun changeAgainDisplayMethod() {

        if (againIsCipherText) {
            // 切换至明文
            StudentUtil.editOpen(password_again_aet, password_again_rule_iv)
        } else {
            // 切换至密文
            StudentUtil.editDismiss(password_again_aet, password_again_rule_iv)
        }
        againIsCipherText = !againIsCipherText
    }


    @OnClick(
        R.id.verify_code_tv,
        R.id.submit_tv,
        R.id.password_rule_iv,
        R.id.password_again_rule_iv
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
                presenter.retrieve(this, phoneValue, verifyValue, passwordValue, passwordAgainValue)
            }
            R.id.password_rule_iv -> {
                changeDisplayMethod()
            }
            R.id.password_again_rule_iv -> {
                changeAgainDisplayMethod()
            }
        }
    }


    override fun retrieveSuccess() {
        ToastUtils.showShort("密码找回成功")
        finish()
    }


}