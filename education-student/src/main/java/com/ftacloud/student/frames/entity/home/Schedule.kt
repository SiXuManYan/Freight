package com.ftacloud.student.frames.entity.home

/**
 * Created by Wangsw on 2020/10/12 0012 19:07.
 * </br>
 *
 */
class Schedule {

    var id = ""
    var courseId = ""
    var courseName = ""
    var courseIntroduce = ""


    /**
     * @see ScheduleCourseType.EXPERIENCE
     * @see ScheduleCourseType.READING
     * @see ScheduleCourseType.TEACHING
     */
    var courseType = ""
    var courseTags = ArrayList<String>()
    var courseMoney = ""
    var courseMoneyOfDiscount = ""

    /**
     *  @see ScheduleState.UNACTIVE
     *  @see ScheduleState.UNTEACH
     *  @see ScheduleState.TAUGHT
     *  @see ScheduleState.TEACHING
     */
    var state = ""


}