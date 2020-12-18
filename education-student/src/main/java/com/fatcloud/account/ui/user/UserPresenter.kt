package com.fatcloud.account.ui.user

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.core.content.FileProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.BuildConfig
import com.fatcloud.account.R
import com.fatcloud.account.common.StudentUtil
import com.fatcloud.account.frames.entity.request.SetUserInfo
import com.fatcloud.account.frames.network.response.BasePresenter
import com.fatcloud.account.storage.entity.User
import com.google.gson.JsonElement
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.util.Common
import com.sugar.library.util.Constants
import com.sugar.library.util.PermissionUtils
import java.io.File
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/10/8 0008 16:26.
 * </br>
 *
 */
class UserPresenter @Inject constructor(private var view: UserView) : BasePresenter(view) {


    /**
     * 拍摄和录制权限申请
     */
    fun requestShootingPermissions(context: Context, file_path: String) {

        PermissionUtils.permissionAny(
            context, PermissionUtils.OnPermissionCallBack { granted ->
                if (granted) {
                    val sdStatus = Environment.getExternalStorageState()
                    if (sdStatus != Environment.MEDIA_MOUNTED) { // 检测sd是否可用
                        ToastUtils.showShort(context.getString(R.string.sd_not_exit))
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


    /**
     * 获取验证码
     *
     */
    fun setUserInfo(
        lifecycle: LifecycleOwner,
        mAvatarUrl: String,
        mNameChinese: String,
        mNameEnglish: String,
        mBirthday: String,
        mEnglishBasis: String
    ) {
//        if (mAvatarUrl.isBlank() && mNameChinese.isBlank() && mNameEnglish.isBlank() && mBirthday.isBlank() && mEnglishBasis.isBlank()) {
//            return
//        }

        val apply = SetUserInfo().apply {
            headImg = mAvatarUrl
            name = mNameChinese
            enName = mNameEnglish
            birthday = mBirthday
            stage = mEnglishBasis
            phone = User.get().username
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.setUserInfo(apply), object : BaseHttpSubscriber<JsonElement>(view) {
                override fun onSuccess(data: JsonElement?) {
                    ToastUtils.showShort(StringUtils.getString(R.string.set_success))
                    val user = User.get().apply {
                        this.headImg = mAvatarUrl
                        this.name = mNameChinese
                        this.enName = mNameEnglish
                        this.birthday = mBirthday
                        this.birthday = mBirthday
                        this.stageValue = mEnglishBasis
                    }
                    database.userDao().updateUser(user)
                    view.setUserInfoSuccess()
                    RxBus.post(Event(Constants.EVENT_UPDATE_USER_INFO))
                }
            }
        )

    }

    fun updateUser(user: User) {
        database.userDao().updateUser(user)
    }

    fun getStages(): ArrayList<String> {
        val stages = ArrayList<String>()
        stages.add(StringUtils.getString(R.string.basis_none))
        stages.add(StringUtils.getString(R.string.basis_a_little))
        stages.add(StringUtils.getString(R.string.basis_none))
        stages.add(StringUtils.getString(R.string.basis_two))
        stages.add(StringUtils.getString(R.string.basis_three))
        stages.add(StringUtils.getString(R.string.basis_four))
        stages.add(StringUtils.getString(R.string.basis_five))
        stages.add(StringUtils.getString(R.string.basis_six))
        return stages
    }


    /**
     *
     */
}