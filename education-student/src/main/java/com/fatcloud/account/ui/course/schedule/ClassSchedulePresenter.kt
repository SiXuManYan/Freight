package com.fatcloud.account.ui.course.schedule

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.BuildConfig
import com.fatcloud.account.frames.entity.ClassSchedule
import com.fatcloud.account.frames.entity.MyCourse
import com.fatcloud.account.frames.entity.request.ListRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import java.util.ArrayList
import java.util.HashMap
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/25 0025 17:20.
 * </br>
 *
 */
class ClassSchedulePresenter @Inject constructor(private var view: ClassScheduleView) : BasePresenter(view) {

    override fun loadList(lifecycle: LifecycleOwner, page: Int, pageSize: Int, lastItemId: String?) {
        val apply = ListRequest().apply {
            lastId = lastItemId
            size = pageSize
        }
        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY, apiService.unTeachCourse(apply), object : BaseJsonArrayHttpSubscriber<MyCourse>(view) {

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

                if (list.isNotEmpty()) {
                    view.bindList(restoreList(list), lastItemId)
                }

            }
        })


    }


    private fun restoreList(list: ArrayList<MyCourse>): ArrayList<ClassSchedule> {

        val parentList = ArrayList<ClassSchedule>()

        // 取出时间
        val timeSet = LinkedHashSet<String>()
        list.forEach {
            timeSet.add(it.studyDatetime)
        }

        val time = ArrayList<String>(timeSet)

        // 整理数据
        time.forEach {

            val classSchedule = ClassSchedule()
            list.forEach { myCourse ->
                if (myCourse.studyDatetime == it) {
                    classSchedule.nativeDate = it
                    classSchedule.nativeDataList.add(myCourse)
                }
            }
            parentList.add(classSchedule)
        }
        return parentList
    }


}