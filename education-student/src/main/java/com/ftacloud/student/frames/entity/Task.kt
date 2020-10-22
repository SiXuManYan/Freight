package com.ftacloud.student.frames.entity

/**
 * Created by Wangsw on 2020/9/27 0027 14:31.
 * </br>
 * 课后任务列表
 */
class Task {


    // 公用
    var productIconImg: String = ""
    var studyDatetime: String = ""
    var taskOfCourseId: String = ""

    //  任务列表
    var productId: Long = 0
    var productName: String = ""


    // 首页任务
    var scheduleId: Long = 0
    var courseId: Long = 0
    var courseName = ""


}