package com.ftacloud.student.ui.course.detail.prepare.child.teacher

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.fragment.BaseFragment
import com.ftacloud.student.frames.entity.NativeTeacher
import com.sugar.library.util.Constants
import kotlinx.android.synthetic.main.fragment_teacher.*

/**
 * Created by Wangsw on 2020/11/2 0002 20:05.
 * </br>
 *
 */
class TeacherFragment : BaseFragment<TeacherPresenter>(), TeacherView {


    companion object {

        fun newInstance(content: NativeTeacher): TeacherFragment {
            val fragment = TeacherFragment()
            val args = Bundle()
            args.putSerializable(Constants.PARAM_TEACHER, content)
            fragment.arguments = args
            return fragment
        }

    }

    override fun getLayoutId()  = R.layout.fragment_teacher

    override fun loadOnVisible() = Unit

    override fun initViews(parent: View) {
        val content = arguments?.getSerializable(Constants.PARAM_TEACHER ) as NativeTeacher

        setData(content)

    }

    private fun setData(content: NativeTeacher) {
        Glide.with(this).load(content.teacherHeadImg).into(avatar_civ)
        teacher_name_tv.text = content.teacherName
        content_tv.text = content.teacherIntroduce
    }


}