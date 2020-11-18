package com.fatcloud.account.ui.course.schedule

import com.fatcloud.account.frames.entity.MyCourse

/**
 * Created by Wangsw on 2020/11/18 0018 15:10.
 * </br>
 *
 */
class CourseComparator :Comparator<MyCourse> {


    /**
     *
                负整数，零或正整数作为第一个参数是小于，等于或大于第二个。
     */
    override fun compare(o1: MyCourse?, o2: MyCourse?): Int {


        return 1

    }


}