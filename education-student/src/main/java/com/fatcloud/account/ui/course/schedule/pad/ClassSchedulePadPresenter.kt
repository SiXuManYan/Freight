package com.fatcloud.account.ui.course.schedule.pad

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.constant.TimeConstants
import com.blankj.utilcode.util.TimeUtils
import com.fatcloud.account.frames.entity.ClassSchedule
import com.fatcloud.account.frames.entity.MyCourse
import com.fatcloud.account.frames.entity.request.ListRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.gson.JsonArray
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import com.sugar.library.util.TimeUtil
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by Wangsw on 2020/11/19 0019 14:21.
 * </br>
 *
 */
class ClassSchedulePadPresenter @Inject constructor(private var view: ClassSchedulePadView) : BasePresenter(view) {


    /**
     * 获取下一周时间
     * yyyy-MM-dd HH:mm:ss
     */
    fun getNextWeekDates(): List<String> {
        val weekList = ArrayList<String>()

        val current = System.currentTimeMillis()
        for (i in 0..6) {
            val timestamp = current + (TimeConstants.DAY * i)

            // yyyy-MM-dd HH:mm:ss
            val weekDate = TimeUtils.millis2String(timestamp)
            weekList.add(weekDate)
        }
        return weekList
    }


    fun loadData(lifecycle: LifecycleOwner) {
        val apply = ListRequest().apply {
            size = 100
        }
        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY, apiService.unTeachCourse(apply), object : BaseJsonArrayHttpSubscriber<MyCourse>(view) {

            override fun onSuccess(jsonArray: JsonArray?, list: java.util.ArrayList<MyCourse>, lastItemId: String?) {


                if (list.isNotEmpty()) {
                    view.bindList(list, lastItemId)
                }

            }
        })


    }


    fun restoreList(list: java.util.ArrayList<MyCourse>, radioButton: MaterialRadioButton): java.util.ArrayList<ClassSchedule> {

        val parentList = java.util.ArrayList<ClassSchedule>()
        val date = radioButton.tag as String

        list.forEach { myCourse ->
//            if (myCourse.studyDatetime == date) {
            if (TimeUtil.isSameDay(TimeUtils.string2Millis(myCourse.studyDatetime), TimeUtils.string2Millis(date), TimeZone.getDefault())) {
                val classSchedule = ClassSchedule().apply {
                    nativeDate = ""
                    nativeDataList.add(myCourse)
                }
                parentList.add(classSchedule)
            }
        }
        return parentList
    }


}