package com.ftacloud.student.ui.user

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.annotation.IdRes
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.BuildConfig
import com.ftacloud.student.R
import com.ftacloud.student.common.StudentUtil
import com.ftacloud.student.frames.network.response.BasePresenter
import com.sugar.library.ui.matisse.Glide4Engine
import com.sugar.library.ui.matisse.Matisse
import com.sugar.library.util.Common
import com.sugar.library.util.Constants
import com.sugar.library.util.PermissionUtils
import com.zhihu.matisse.MimeType
import java.io.File
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/10/8 0008 16:26.
 * </br>
 *
 */
class UserPresenter @Inject constructor(private var view: UserView): BasePresenter(view){


    /**
     * 拍摄和录制权限申请
     */
    fun requestShootingPermissions(context: Context, file_path:String) {

        PermissionUtils.permissionAny(
            context, PermissionUtils.OnPermissionCallBack { granted ->
                if (granted) {
                    val sdStatus = Environment.getExternalStorageState()
                    if (sdStatus != Environment.MEDIA_MOUNTED) { // 检测sd是否可用
                        ToastUtils.showShort("SD卡不存在")
                    } else {
                        Common.FILE_NAME = String.format(Common.COMMON_PHOTO_NAME, System.currentTimeMillis())
                        val fileParent = File(file_path)
                        if (!fileParent.exists()) {
                            fileParent.mkdirs()
                        }
                        val file = File(file_path + Common.FILE_NAME)
                        val uri: Uri
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file)// 7.0以后
                        } else {
                            uri = Uri.fromFile(file)
                        }
                        view.onShootingPermissionResult(uri)



                    }

                } else {
                    StudentUtil.showPermissionFailure(context!!, "为了正常使用拍摄功能，请允许存储空间权限和相机权限")
                }
            },
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

    }





}