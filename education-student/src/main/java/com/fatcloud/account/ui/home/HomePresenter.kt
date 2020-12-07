package com.fatcloud.account.ui.home

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.frames.entity.Task
import com.fatcloud.account.frames.entity.home.*
import com.fatcloud.account.frames.network.response.BasePresenter
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.sugar.library.frames.extend.genericType
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
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
            val elements = gson.fromJson<ArrayList<Test>>(data.get(quizzesOfStudentOuts), genericType<ArrayList<Test>>())
            parentList.addAll(elements)
            if (elements.isNotEmpty()) {
                val quizzesOfStudentId = elements[0].quizzesOfStudentId
                val quizzesId = elements[0].quizzesId
                CommonUtils.getShareStudent().put(Constants.SP_QUIZZES_OF_STUDENT_ID, quizzesOfStudentId)
                CommonUtils.getShareStudent().put(Constants.SP_QUIZZES_ID, quizzesId)
            }
        }

        // 体验课
        val experienceClass = ArrayList<Course>()

        // 普通课
        val commonClass = ArrayList<Course>()

        // 课程表
        val classSchedule = ArrayList<NativeClassSchedule>()

        // 课程
        if (data.has(scheduleOuts)) {

            // 源
            val scheduleOuts = gson.fromJson<ArrayList<Course>>(data.get(scheduleOuts), genericType<ArrayList<Course>>())
            val nativeClassSchedule = NativeClassSchedule()
            scheduleOuts.forEach {

                if (it.productType.contains(CourseProductType.EXPERIENCE.name)) {
                    experienceClass.add(it)
                } else {
                    commonClass.add(it)
                }

                // 课程表(所有未上课的数据)
                if (it.state.contains(CourseState.UNTEACH.name)) {
                    nativeClassSchedule.apply { nativeData.add(it) }
                }
            }
            if (nativeClassSchedule.nativeData.isNotEmpty()) {
                classSchedule.add(nativeClassSchedule)
            }
        }

        // 订单
        if (data.has(waitPayOrderOuts)) {
            val scheduleOuts = gson.fromJson<ArrayList<HomeOrder>>(data.get(waitPayOrderOuts), genericType<ArrayList<HomeOrder>>())
            parentList.addAll(scheduleOuts)
        }

        //  体验课
        parentList.addAll(experienceClass)

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