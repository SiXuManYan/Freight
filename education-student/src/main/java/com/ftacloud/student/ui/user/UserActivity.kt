package com.ftacloud.student.ui.user

import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.ftacloud.student.R
import com.ftacloud.student.common.OssUtil
import com.ftacloud.student.common.StudentUtil
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.storage.entity.User
import com.ftacloud.student.ui.app.CloudAccountApplication
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sugar.library.ui.matisse.Matisse
import com.sugar.library.util.Common
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import com.sugar.library.util.ProductUtils
import kotlinx.android.synthetic.main.activity_user.*

/**
 * Created by Wangsw on 2020/10/8 0008 16:27.
 * </br>
 *
 */
class UserActivity : BaseMVPActivity<UserPresenter>(), UserView {

    private val filePath = Environment.getExternalStorageDirectory().toString() + Common.IMAGE_SAVE_PATH

    override fun getLayoutId() = R.layout.activity_user

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initViews() {
        initAvatar()
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



    @OnClick(
        R.id.avatar_rl,
        R.id.birthday_rl
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
            StudentUtil.handleMediaSelect(this, Matisse.IMG, view.id)
            dialog.dismiss()
        }
        view.findViewById<TextView>(R.id.cancel_tv).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

}