package com.fatcloud.account.ui.home.holder

import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.TimeUtils
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.home.NativeClassSchedule
import com.fatcloud.account.frames.entity.home.Course
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.util.TimeUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_class_schedule.*
import java.util.ArrayList

/**
 * Created by Wangsw on 2020/10/13 0013 11:17.
 * </br>
 *  课程表
 */
class ScheduleHolder(parent: ViewGroup?) : BaseItemViewHolder<NativeClassSchedule>(parent, R.layout.item_home_class_schedule), LayoutContainer {

    override val containerView: View? get() = itemView


    var mon = ArrayList<Course>()
    var tues = ArrayList<Course>()
    var wed = ArrayList<Course>()
    var thur = ArrayList<Course>()
    var fri = ArrayList<Course>()
    var sta = ArrayList<Course>()
    var sun = ArrayList<Course>()


    override fun setData(data: NativeClassSchedule?) {

        if (data == null) {
            return
        }
        if (data.nativeData.isEmpty()) {
            return
        }

        data.nativeData.forEach {

            val chineseWeek = TimeUtils.getChineseWeek(it.studyDatetime)
            when (chineseWeek) {
                "星期一" -> {
                    mon.add(it)
                }
                "星期二" -> {
                    tues.add(it)
                }
                "星期三" -> {
                    wed.add(it)
                }
                "星期四" -> {
                    thur.add(it)
                }
                "星期五" -> {
                    fri.add(it)
                }
                "星期六" -> {
                    sta.add(it)
                }
                else -> {
                    sun.add(it)
                }
            }
        }


        if (sun.isNotEmpty()) {

            zero_tv.text =  TimeUtil.getFormatTimeHM(sun[0].studyDatetime)
        }

        if (mon.isNotEmpty()) {
            one_tv.text = mon[0].productName
        }

        if (tues.isNotEmpty()) {
            two_tv.text = tues[0].productName
        }

        if (wed.isNotEmpty()) {
            three_tv.text = wed[0].productName
        }

        if (thur.isNotEmpty()) {
            four_tv.text = thur[0].productName
        }

        if (fri.isNotEmpty()) {
            five_tv.text = fri[0].productName
        }

        if (sta.isNotEmpty()) {
            six_tv.text = sta[0].productName
        }


    }

}