package com.ftacloud.student.frames.entity

/**
 * Created by Wangsw on 2020/11/2 0002 19:24.
 * </br>
 *
 */
class FormalCourseDetail {

    var teacherHeadImg = ""

    var teacherName = ""

    var teacherIntroduce = ""

    var productIntroduce = ""

    var productName = ""

    var productId = ""

    var liveRoomNo = ""

    var scheduleOuts = ArrayList<ScheduleOut>()


    class ScheduleOut {
        var courseName: String = ""
        var lengthOfCourse = ""
        var courseId = ""
        var scheduleId = ""
        var studyDatetime = ""
        var scheduleState = ""
    }

}