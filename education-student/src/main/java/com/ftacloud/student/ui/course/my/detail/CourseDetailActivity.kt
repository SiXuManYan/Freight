package com.ftacloud.student.ui.course.my.detail

import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import butterknife.OnClick
import com.blankj.utilcode.util.SizeUtils
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.entity.CourseDetail
import com.sugar.library.ui.view.CircleImageView
import com.sugar.library.util.CommonUtils
import kotlinx.android.synthetic.main.activity_course_detail.*

/**
 * Created by Wangsw on 2020/9/23 0023 9:57.
 * </br>
 *  课程详情
 */
class CourseDetailActivity : BaseMVPActivity<CourseDetailPresenter>(), CourseDetailView {


    override fun getLayoutId() = R.layout.activity_course_detail

    override fun initViews() {

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


        study_tv.text = it.nativeFakerPrice

    }


    @OnClick(
        R.id.study_tv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.study_tv -> {

            }
            else -> {
            }
        }
    }


}