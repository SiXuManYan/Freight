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