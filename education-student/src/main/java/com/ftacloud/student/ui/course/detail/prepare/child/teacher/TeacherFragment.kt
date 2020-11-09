package com.ftacloud.student.ui.course.detail.prepare.child.teacher

import android.view.View
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.fragment.BaseFragment
import com.ftacloud.student.frames.entity.FormalCourseDetail
import com.ftacloud.student.frames.event.NoClassDataEvent
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_teacher.*

/**
 * Created by Wangsw on 2020/11/2 0002 20:05.
 * </br>
 *
 */
class TeacherFragment : BaseFragment<TeacherPresenter>(), TeacherView {




    override fun getLayoutId()  = R.layout.fragment_teacher

    override fun loadOnVisible() = Unit

    override fun initViews(parent: View) {

        presenter.subsribeEventEntity(Consumer<NoClassDataEvent> {
            setData(it.data)
        })
    }

    private fun setData(content: FormalCourseDetail) {
        Glide.with(this).load(content.teacherHeadImg).into(avatar_civ)
        teacher_name_tv.text = content.teacherName
        content_tv.text = content.teacherIntroduce
    }


}