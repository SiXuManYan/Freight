package com.ftacloud.student.ui.user

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.OnClick
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.common.OssUtil
import com.ftacloud.student.common.StudentUtil
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.storage.entity.User
import com.ftacloud.student.ui.app.CloudAccountApplication
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sugar.library.event.ImageUploadEvent
import com.sugar.library.ui.matisse.Matisse
import com.sugar.library.ui.view.CircleImageView
import com.sugar.library.util.Common
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_user.*
import java.io.File
import java.util.*

/**
 * Created by Wangsw on 2020/10/8 0008 16:27.
 * </br>
 *
 */
class UserActivity : BaseMVPActivity<UserPresenter>(), UserView {

    private val filePath = Environment.getExternalStorageDirectory().toString() + Common.IMAGE_SAVE_PATH

    private var mAvatarUrl = ""
    private var mNameChinese = ""
    private var mNameEnglish = ""
    private var mBirthdayString = ""
    private var mEnglishBasis = ""

    override fun getLayoutId() = R.layout.activity_user

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initViews() {
        setMainTitle(R.string.user_detail_title)
        initAvatar()
        initEvent()
    }



    private fun initEvent() {
        // 图片上传成功
        presenter.subsribeEventEntity<ImageUploadEvent>(Consumer {
            if (it.formWhichClass != this.javaClass) {
                return@Consumer
            }
            mAvatarUrl = it.finalUrl
        })

    }

    private fun initAvatar() {
        val headUrl = User.get().headImg
        if (headUrl.isBlank()) {
            return
        }
        OssUtil.getRealOssUrl(this, headUrl, object : CloudAccountApplication.OssSignCallBack {
            override fun ossUrlSignEnd(url: String) {
                Glide.with(context!!).load(url).error(R.drawable.ic_sliding_avatar).into(avatar_civ)
            }
        })

    }

    override fun onShootingPermissionResult(uri: Uri?) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, Constants.REQUEST_CAMERA)
    }

    override fun setUserInfoSuccess() {
        finish()
    }


    @OnClick(
        R.id.avatar_rl,
        R.id.birthday_rl,
        R.id.commit_tv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.avatar_rl -> {
                avatarClick()
            }
            R.id.birthday_rl -> {
                selectBirthday()
            }
            R.id.commit_tv -> {
                presenter.setUserInfo(this, mAvatarUrl, mNameChinese, mNameEnglish, mBirthdayString, mEnglishBasis)
            }
            else -> {

            }
        }
    }


    private fun avatarClick() {

        val dialog = BottomSheetDialog(context!!)
        val view = LayoutInflater.from(this).inflate(R.layout.post_feed_bottom_sheet, null)
        dialog.setContentView(view)
        try {
            val parent = view.parent as ViewGroup
            parent.setBackgroundResource(android.R.color.transparent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        view.findViewById<TextView>(R.id.shooting).setOnClickListener {
            // 拍摄
            presenter.requestShootingPermissions(this, filePath)
            dialog.dismiss()
        }

        view.findViewById<TextView>(R.id.select_album).setOnClickListener {
            StudentUtil.handleMediaSelect(this, Matisse.IMG, R.id.avatar_civ)
            dialog.dismiss()
        }
        view.findViewById<TextView>(R.id.cancel_tv).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        when (requestCode) {

            Constants.REQUEST_MEDIA -> {
                if (data == null) {
                    return
                }

                // 相册选择图片
                val elements = Matisse.obtainPathResult(data)
                if (elements.isNotEmpty()) {
                    val fileDirPath = elements[0]
                    val fromViewId = data.getIntExtra(Matisse.MEDIA_FROM_VIEW_ID, 0)
                    if (fromViewId != 0) {
                        val fromView = findViewById<CircleImageView>(fromViewId)
                        if (fromView != null) {
                            Glide.with(this).load(fileDirPath).into(fromView)
                        }
                    }
                    val application = application as CloudAccountApplication
                    application.getOssSecurityToken(fileDirPath, fromViewId, this.javaClass)
                }
            }
            Constants.REQUEST_CAMERA -> {

                // 相机拍摄
                val pathName = filePath + Common.FILE_NAME
                val f = File(pathName)
                if (f.exists()) {
                    val absolutePath = f.absolutePath
                    Glide.with(this).load(absolutePath).into(avatar_civ)
                    val application = application as CloudAccountApplication
                    application.getOssSecurityToken(absolutePath, R.id.avatar_civ, this.javaClass)

                } else {
                    // 拍摄失败
                    ToastUtils.showShort(R.string.capture_error)
                }
            }
        }
    }


    private fun selectBirthday() {
        val ca = Calendar.getInstance()
        val mYear = ca[Calendar.YEAR]
        val mMonth = ca[Calendar.MONTH]
        val mDay = ca[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            mBirthdayString = getString(R.string.birthday_format, year, (month + 1), dayOfMonth)
            birthday_tv.text = mBirthdayString

        }, mYear, mMonth, mDay)
        datePickerDialog.show()
    }


}