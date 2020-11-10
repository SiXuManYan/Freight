package com.ftacloud.student.ui.course.detail.experience

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import butterknife.OnClick
import com.blankj.utilcode.util.SizeUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.common.OssUtil
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.entity.CourseDetail
import com.ftacloud.student.frames.entity.home.HomeOrderExtra
import com.ftacloud.student.ui.app.CloudAccountApplication
import com.ftacloud.student.ui.course.detail.experience.result.ReservationResultActivity
import com.ftacloud.student.ui.order.pay.prepare.PayPrepareActivity
import com.sugar.library.ui.view.CircleImageView
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import kotlinx.android.synthetic.main.activity_course_detail.*

/**
 * Created by Wangsw on 2020/9/23 0023 9:57.
 * </br>
 *  体验课详情
 */
class ExperienceCourseDetailActivity : BaseMVPActivity<ExperienceCourseDetailPresenter>(), ExperienceCourseDetailView {

    companion object {
        /** 体验课 */
        val RESERVE = 0

        /** 订单 */
        val PAY = 1
    }

    var homeOrderExtra: HomeOrderExtra? = null
    var type = 0

    override fun getLayoutId() = R.layout.activity_course_detail

    override fun initViews() {
        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_ID) || !intent.extras!!.containsKey(Constants.PARAM_TYPE)) {
            finish()
            return
        }
        val scheduleId = intent.extras!!.getString(Constants.PARAM_ID, "")

        type = intent.extras!!.getInt(Constants.PARAM_TYPE)

        if (type == RESERVE) {
            switcher_vs.displayedChild = 0
            presenter.getDetail(this, scheduleId)
        } else {
            switcher_vs.displayedChild = 1
            if (intent.extras!!.containsKey(Constants.PARAM_ORDER)) {
                homeOrderExtra = intent.getSerializableExtra(Constants.PARAM_ORDER) as HomeOrderExtra
            }
            presenter.getOrderCourseDetail(this, homeOrderExtra!!.orderId, homeOrderExtra!!.productId)
        }
    }

    override fun bindData(it: CourseDetail) {

        student_container_ll.removeAllViews()

        it.studentHeadImgs.forEach {
            val circleImageView = CircleImageView(this)
            circleImageView.layoutParams = LinearLayout.LayoutParams(SizeUtils.dp2px(25f), SizeUtils.dp2px(25f)).apply {
                marginEnd = SizeUtils.dp2px(5f)
            }

            OssUtil.getRealOssUrl(this, it, object : CloudAccountApplication.OssSignCallBack {
                override fun ossUrlSignEnd(url: String) {
                    Glide.with(this@ExperienceCourseDetailActivity).load(url).into(circleImageView)
                }
            })
            student_container_ll.addView(circleImageView)
        }

        student_count_tv.text = getString(R.string.student_count_format, it.studentCount)

        if (it.teacherHeadImg.isNotBlank() || it.teacherName.isNotBlank() || it.teacherIntroduce.isNotBlank()) {
            OssUtil.getRealOssUrl(this, it.teacherHeadImg, object : CloudAccountApplication.OssSignCallBack {
                override fun ossUrlSignEnd(url: String) {
                    Glide.with(this@ExperienceCourseDetailActivity).load(url).into(teacher_iv)
                }
            })
            teacher_name_tv.text = it.teacherName
            teacher_introduction.text = it.teacherIntroduce
            teacher_ll.visibility = View.VISIBLE
        } else {
            teacher_ll.visibility = View.GONE
        }

        course_container_ll.removeAllViews()
        it.courseDetailImgs.forEach {

            val iv = ImageView(this)
            iv.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
                bottomMargin = SizeUtils.dp2px(5f)
            }
            course_container_ll.addView(iv)

            OssUtil.getRealOssUrl(this@ExperienceCourseDetailActivity, it, object : CloudAccountApplication.OssSignCallBack {
                override fun ossUrlSignEnd(url: String) {
                    Glide.with(this@ExperienceCourseDetailActivity).load(url).into(iv)
                }
            })
        }
        price_tv.text = getString(R.string.money_symbol_format, it.payingMoney)
    }

    override fun bookingExperienceSuccess() {
        ToastUtils.showShort("预约成功")
    }


    @OnClick(
        R.id.buy_tv,
        R.id.reservation_tv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.buy_tv -> {
                startActivity(PayPrepareActivity::class.java, Bundle().apply {
                    putSerializable(Constants.PARAM_ORDER, homeOrderExtra)
                })
            }
            R.id.reservation_tv -> {
                startActivity(ReservationResultActivity::class.java)
            }
            else -> {

            }
        }
    }

}