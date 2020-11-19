package com.fatcloud.account.ui.course.my.child

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.BuildConfig
import com.fatcloud.account.frames.entity.MyCourse
import com.fatcloud.account.frames.entity.request.ListRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.fatcloud.account.ui.course.my.MyCourseActivity.Companion.COURSE_UN_TEACH
import com.google.gson.JsonArray
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/27 0027 10:03.
 * </br>
 *
 */
class MyCourseChildPresenter @Inject constructor(private var view: MyCourseChildView) : BasePresenter(view) {

    fun loadCourseList(lifecycle: LifecycleOwner, pageSize: Int, lastItemId: String?, categoryValue: Int?) {
        val apply = ListRequest().apply {
            lastId = lastItemId
            size = pageSize
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            when (categoryValue) {

                COURSE_UN_TEACH -> {
                    apiService.unTeachCourse(apply)
                }
                else -> {
                    apiService.listTaught(apply)
                }
            },

            object : BaseJsonArrayHttpSubscriber<MyCourse>(view) {

                override fun onSuccess(jsonArray: JsonArray?, list: ArrayList<MyCourse>, lastItemId: String?) {
                    if (BuildConfig.DEBUG) {


                        val apply1 = MyCourse().apply {
                            productId = "1"
                            courseName = "小学一年级上册第1章"
                            state = "UNTEACH-未上课"
                            teacherHeadImg =
                                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605676239556&di=1424d583e834b032c2ad8011711f9dea&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201604%2F22%2F20160422125942_RiF32.jpeg"
                            productName = "sugar"
                            courseId = "1"
                            countDownStudySeconds = "0"
                            teacherName = "糖糖老师"
                            orderId = "1321375044911370240"
                            scheduleId = "10"
                            productIconImg = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3979510287,3948598266&fm=26&gp=0.jpg"
                            courseIntroduce = "tangtang"
                        }
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)

                    }

                    view.bindList(list, lastItemId)
                }
            }
        )

    }

}