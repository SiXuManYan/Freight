package com.fatcloud.account.ui.gallery


import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.viewpager.widget.ViewPager
import butterknife.OnClick
import com.blankj.utilcode.util.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseActivity
import com.fatcloud.account.frames.glide.OnProgressListener
import com.fatcloud.account.frames.glide.ProgressImageViewTarget
import com.github.chrisbanes.photoview.PhotoView
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.sugar.library.ui.widget.dialog.AlertDialog
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_gallery.*
import java.io.File

/**
 * Created by Wangsw on 2020/6/29
 * </br>
 *
 */
class GalleryActivity : BaseActivity() {


    private var imageCount = 0
    private var index = -1
    private var list: ArrayList<String>? = null
    private var rxPermission: RxPermissions? = null
    private var disposable: Disposable? = null
    private var hashCode: Int? = null

    override fun getLayoutId() = R.layout.activity_gallery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    override fun onDestroy() {
        disposable?.dispose()
        disposable = null
        rxPermission = null

        super.onDestroy()
    }

    override fun initViews() {
        overridePendingTransition(R.anim.fade_in_alpha, 0)
        initExtra()
        initView()
    }

    private fun initExtra() {
        intent.extras?.let {
            index = it.getInt(Constants.PARAM_INDEX)
            list = it.getStringArrayList(Constants.PARAM_LIST)
            if (!it.containsKey(Constants.PARAM_SAVE)) {
                iv_save.visibility = View.GONE
            }
            imageCount = list!!.size
            if (it.containsKey(Constants.PARAM_HASH_CODE)) {
                hashCode = it.getInt(Constants.PARAM_HASH_CODE)
            }
        }
    }

    private fun initView() {
        CommonUtils.setStatusBarTransparent(this)
        BarUtils.setNavBarVisibility(this, false)


        val layoutParams = title_rl.layoutParams as RelativeLayout.LayoutParams
        layoutParams.topMargin = BarUtils.getStatusBarHeight()

        pager.adapter = ImagePageAdapter()
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                tv_title.text = getString(R.string.image_position_format, (position + 1), imageCount)
                 // 同步外部index 坐标
//                if (hashCode != null) {
//                    RxBus.post(GallerySelectEvent(hashCode!!, pager.currentItem))
//                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        if (index > 0) {
            pager.setCurrentItem(index, false)
        } else {
            tv_title.text = getString(R.string.image_position_format, 1, imageCount)
        }
        rxPermission = RxPermissions(this)
    }


    @OnClick(R.id.iv_save)
    fun onSaveClick() {
        disposable = rxPermission?.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)?.subscribe { isGranted ->
            AlertDialog.Builder(this)
                    .setCancelable(true)
                    .setMessage(if (isGranted) {
                        R.string.ask_image_save_to_device
                    } else {
                        R.string.ask_permission_to_save_image
                    })
                    .setPositiveButton(R.string.yes, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, _ ->
                        dialog.dismiss()
                        if (isGranted) {
                            val savePath = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath, "Cloud")
                            val destFile = File(savePath, "${System.currentTimeMillis()}.jpg")
                            FileUtils.createOrExistsFile(destFile)
                            Observable.just(destFile)
                                    .map { dest ->
                                        val future = Glide.with(context!!).asFile().load(list!![pager.currentItem]).submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                        future.get().copyTo(dest, true)
                                        return@map dest
                                    }
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribe({ dest ->
                                        sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                                Uri.fromFile(dest)))
                                        ToastUtils.showShort(R.string.image_save_success)
                                    }, {
                                        LogUtils.e(it.message)
                                    })
                        } else {
                            AppUtils.launchAppDetailsSettings()
                        }
                    })
                    .setNegativeButton(R.string.no, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, _ ->
                        dialog.dismiss()
                    })
                    .create().show()
        }
    }

    inner class ImagePageAdapter : androidx.viewpager.widget.PagerAdapter() {

        override fun getCount(): Int {
            return imageCount
        }

        override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
            return arg0 === arg1
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {


            val view = layoutInflater.inflate(R.layout.item_gallery, null)

            val photo_view = view.findViewById<PhotoView>(R.id.photo_view).apply {
                maximumScale = 10.0f
                setOnClickListener {
                    onBackPressed()
                }
            }

            val pb_loading = view.findViewById<CircularProgressBar>(R.id.cpb_image_progress)

            Glide.with(this@GalleryActivity).load(list!![position])
//                    .thumbnail(Glide.with(this@GalleryActivity).load(list!![position]))
                    .into(ProgressImageViewTarget(photo_view, list!![position], object : OnProgressListener {
                        override fun onProgress(isComplete: Boolean, percentage: Int, bytesRead: Long, totalBytes: Long) {
                            LogUtils.d(percentage)

                            if (isComplete) {
                                pb_loading.visibility = View.GONE
                            } else {
                                pb_loading.visibility = View.VISIBLE
                                pb_loading.progress = percentage.toFloat()
                            }
                        }
                    }))
            container.addView(view)
            return view
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, R.anim.fade_out_alpha)
    }

}