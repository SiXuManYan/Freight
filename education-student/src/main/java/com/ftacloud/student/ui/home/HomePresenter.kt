package com.ftacloud.student.ui.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.entity.Task
import com.ftacloud.student.frames.entity.home.*
import com.ftacloud.student.frames.network.response.BasePresenter
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.sugar.library.frames.extend.genericType
import io.reactivex.functions.Function
import io.reactivex.subscribers.ResourceSubscriber
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/21 0021 19:30.
 * </br>
 *
 */
class HomePresenter @Inject constructor(private var view: HomeView) : BasePresenter(view) {

    val gson = Gson()

    /** 基础测验 */
    val quizzesOfStudentOuts = "quizzesOfStudentOuts"

    /** 体验课 和课程*/
    val scheduleOuts = "scheduleOuts"

    /** 订单 */
    val waitPayOrderOuts = "waitPayOrderOuts"


    /** 课后任务 */
    val taskOfCourseOuts = "taskOfCourseOuts"


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

        val parentList = ArrayList<Any>()

        // 基础测试
        if (data.has(quizzesOfStudentOuts)) {
            parentList.addAll(gson.fromJson<ArrayList<Test>>(data.get(quizzesOfStudentOuts), genericType<ArrayList<Test>>()))
        }

        // 体验课
        val experienceClass = ArrayList<Schedule>()

        // 普通课
        val commonClass = ArrayList<Schedule>()

        // 课程表
        val classSchedule = ArrayList<NativeClassSchedule>()

        // 课程
        if (data.has(scheduleOuts)) {

            // 源
            val scheduleOuts = gson.fromJson<ArrayList<Schedule>>(data.get(scheduleOuts), genericType<ArrayList<Schedule>>())

            scheduleOuts.forEach {

                if (it.productType.contains(ScheduleProductType.EXPERIENCE.name)) {
                    experienceClass.add(it)
                } else {
                    commonClass.add(it)
                }

                // 课程表(所有未上课的数据)
                if (it.state.contains(ScheduleState.UNTEACH.name)) {
                    classSchedule.add(NativeClassSchedule().apply {
                        nativeData.add(it)
                    })
                }
            }
        }

        //  体验课
        parentList.addAll(experienceClass)

        // 订单
        if (data.has(waitPayOrderOuts)) {
            val scheduleOuts = gson.fromJson<ArrayList<HomeOrder>>(data.get(waitPayOrderOuts), genericType<ArrayList<HomeOrder>>())
            parentList.addAll(scheduleOuts)
        }

        // 普通课
        parentList.addAll(commonClass)

        // 课程表
        parentList.addAll(classSchedule)

        // 课后任务
        if (data.has(taskOfCourseOuts)) {
            val task = gson.fromJson<ArrayList<Task>>(data.get(taskOfCourseOuts), genericType<ArrayList<Task>>())
            parentList.addAll(task)
        }

        return parentList

    }

}