package com.ftacloud.student.common

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.baijiayun.groupclassui.InteractiveClassUI
import com.baijiayun.livecore.utils.LPRxUtils
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.R
import com.sugar.library.ui.matisse.Glide4Engine
import com.sugar.library.ui.matisse.Matisse
import com.sugar.library.ui.view.dialog.AlertDialog
import com.sugar.library.util.Constants
import com.sugar.library.util.PermissionUtils
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.zhihu.matisse.MimeType
import kotlinx.android.synthetic.main.item_home_course_common.*
import java.util.concurrent.TimeUnit

/**
 * Created by Wangsw on 2020/10/8 0008 9:50.
 * </br>
 *
 */
object StudentUtil {


    fun editOpen(editText: EditText, imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_login_eye_open)
        editText.transformationMethod = HideReturnsTransformationMethod.getInstance();
        editText.setSelection(editText.text.toString().length)
    }


    fun editDismiss(editText: EditText, imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_login_eye_close)
        editText.transformationMethod = PasswordTransformationMethod.getInstance();
        editText.setSelection(editText.text.toString().length)
    }


    fun showPermissionFailure(context: Context?, message: String) {
        AlertDialog.Builder(context).setTitle(R.string.hint)
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(context!!.getString(R.string.yes), AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
                AppUtils.launchAppDetailsSettings()
            })
            .setNegativeButton(context.getString(R.string.no), AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
            .create()
            .show()
    }

    fun handleMediaSelectForFragment(fragment: Fragment, mediaType: Int, @IdRes fromViewId: Int) {

        PermissionUtils.permissionAny(
            fragment.activity, PermissionUtils.OnPermissionCallBack { granted ->
                if (granted) {
                    Matisse.from(fragment)
                        .choose(if (mediaType == 0) MimeType.ofAll() else with(MimeType.ofImage()) {
                            remove(MimeType.GIF)
                            this
                        }, true)
                        .countable(true)
                        //                .originalEnable(false)
                        .maxSelectable(1)
                        .theme(R.style.Matisse_Dracula)
                        .thumbnailScale(0.87f)
                        .imageEngine(Glide4Engine())
                        .forResult(Constants.REQUEST_MEDIA, mediaType, fromViewId)

                } else {
                    showPermissionFailure(fragment.context, StringUtils.getString(R.string.album_need_permission))
                }
            }, Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

    }


    /**
     * 相册选择
     * @param mediaType
     * @see Matisse.IMG
     * @see Matisse.GIF
     * @see Matisse.VIDEO
     * @see Constants.I1
     * @see Constants.I2
     */
    fun handleMediaSelect(activity: Activity, mediaType: Int, @IdRes fromViewId: Int) {

        //
        PermissionUtils.permissionAny(
            activity, PermissionUtils.OnPermissionCallBack { granted ->
                if (granted) {
                    Matisse.from(activity)
                        .choose(if (mediaType == 0) MimeType.ofAll() else with(MimeType.ofImage()) {
                            remove(MimeType.GIF)
                            this
                        }, true)
                        .countable(true)
                        // .originalEnable(false)
                        .maxSelectable(1)
                        .theme(R.style.Matisse_Dracula)
                        .thumbnailScale(0.87f)
                        .imageEngine(Glide4Engine())
                        .forResult(Constants.REQUEST_MEDIA, mediaType, fromViewId)

                } else {
                    showPermissionFailure(activity, StringUtils.getString(R.string.album_need_permission))
                }
            }, Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

    }

    /**
     * 判断 用户是否安装微信客户端
     */
    fun isWeixinAvilible(context: Context?): Boolean {
        val iwxapi: IWXAPI = WXAPIFactory.createWXAPI(context, "wxd47f6921c3df9750", false)
        return if (iwxapi.isWXAppInstalled()) {
            true
        } else {
            ToastUtils.showShort("检测到手机未安装微信")
            false
        }
    }


    @SuppressLint("CheckResult")
    fun enterLiveRoom(context: Context, view: View, joinCode: String, studentName: String?) {

        LPRxUtils.clicks(view)
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe {
                if (joinCode.isBlank()) {
                    ToastUtils.showShort("未找到教室")
                    return@subscribe
                }
                if (studentName.isNullOrBlank()) {
                    ToastUtils.showShort("学生姓名不能为空 ")
                    return@subscribe
                }
                InteractiveClassUI.enterRoom(context, joinCode, studentName) { msg ->
                    ToastUtils.showShort(msg)
                }
            }
    }

}