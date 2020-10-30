package com.ftacloud.student.ui.course.detail.experience

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import butterknife.OnClick
import com.blankj.utilcode.util.SizeUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.entity.CourseDetail
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


    override fun getLayoutId() = R.layout.activity_course_detail

    override fun initViews() {
        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_ID) || !intent.extras!!.containsKey(Constants.PARAM_TYPE)) {
            finish()
            return
        }
        val scheduleId = intent.extras!!.getString(Constants.PARAM_ID, "")
        val type = intent.extras!!.getInt(Constants.PARAM_TYPE)

        presenter.getCourseDetail(this, scheduleId)
        if (type == 0) {
            switcher_vs.displayedChild = 0
        } else {
            switcher_vs.displayedChild = 1
        }
    }

    override fun bindData(it: CourseDetail) {

        student_container_ll.removeAllViews()

        it.unteachStudentHeadImgs.forEach {
            val circleImageView = CircleImageView(this)
            circleImageView.layoutParams = LinearLayout.LayoutParams(SizeUtils.dp2px(25f), SizeUtils.dp2px(25f)).apply {
                marginEnd = SizeUtils.dp2px(5f)
            }
            Glide.with(this).load(it).into(circleImageView)
            student_container_ll.addView(circleImageView)
        }

        student_count_tv.text = getString(R.string.student_count_format, it.unteachStudentCount)

        Glide.with(this).load(it.teacherHeadImg).into(teacher_iv)
        teacher_name_tv.text = it.teacherName
        teacher_introduction.text = it.teacherIntroduce


        it.courseDetailImgs.forEach {

            val inflate = LayoutInflater.from(this).inflate(R.layout.item_course_detail_image, null)
            val courseImageView = inflate.findViewById<ImageView>(R.id.course_detail_iv)
            Glide.with(this).load(it).into(courseImageView)

        }

        buy_tv.text = it.nativeFakerPrice

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

            }
            R.id.reservation_tv -> {
                finish()
            }
            else -> {

            }
        }
    }

}