package com.ftacloud.student.ui.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.entity.Task
import com.ftacloud.student.frames.entity.home.NativeClassSchedule
import com.ftacloud.student.frames.entity.home.Quizzes
import com.ftacloud.student.frames.entity.home.Schedule
import com.ftacloud.student.frames.entity.home.ScheduleState
import com.ftacloud.student.frames.network.response.BasePresenter
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.sugar.library.frames.extend.genericType
import io.reactivex.functions.Function
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/21 0021 19:30.
 * </br>
 *
 */
class HomePresenter @Inject constructor(private var view: HomeView) : BasePresenter(view) {

    val gson = Gson()

    val quizzesOfStudentOuts = "quizzesOfStudentOuts"
    val scheduleOuts = "scheduleOuts"
    val task = "task"


    override fun loadList(lifecycle: LifecycleOwner, page: Int) {

        var jsonObject: JsonObject? = null

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY, apiService.getHomeInfo(), Function {
            jsonObject = it.data!!
            return@Function resortList(jsonObject!!)
        }, object : ResourceSubscriber<ArrayList<Any>>() {

            override fun onComplete() = Unit

            override fun onError(t: Throwable?) = Unit

            override fun onNext(data: ArrayList<Any>) {
                view.bindList(data, isFirstPage = true, last = true)
            }
        })

    }


    private fun resortList(data: JsonObject): ArrayList<Any> {
        if (data.isJsonNull) {
            return ArrayList()
        }
        val list = ArrayList<Any>()


        if (data.has(quizzesOfStudentOuts)) {
            // 基础测试
            list.addAll(gson.fromJson<ArrayList<Quizzes>>(data.get(quizzesOfStudentOuts), genericType<ArrayList<Quizzes>>()))
        }


        if (data.has(scheduleOuts)) {
            // 体验课程，订单课程
            val scheduleOuts = gson.fromJson<ArrayList<Schedule>>(data.get(scheduleOuts), genericType<ArrayList<Schedule>>())
            list.addAll(scheduleOuts)


            // 课程表
            val classSchedule = ArrayList<NativeClassSchedule>()
            scheduleOuts.forEach {
                if (it.state == ScheduleState.UNTEACH.name) {
                    classSchedule.add(NativeClassSchedule().apply {
                        nativeData.add(it)
                    })
                }
            }
            list.addAll(scheduleOuts)

        }


        if (data.has(task)) {
            // 课后任务
            list.addAll(gson.fromJson<ArrayList<Task>>(data.get(task), genericType<ArrayList<Task>>()))
        }

        return list

    }

}