package com.ftacloud.student.frames.entity

/**
 * Created by Wangsw on 2020/9/27 0027 10:04.
 * </br>
 * 我的课程
 */
class MyCourse {


/*    {
        productId = 1,
        courseName = 小学一年级上册第1章,
        state = UNTEACH-未上课,
        teacherHeadImg = dev/teacher/ios/20201015220857542.png,
        productName = 1v1体验课,
        courseId = 1,
        countDownStudySeconds = -4853,
        teacherName = 刁老师,
        orderId = 1321375044911370240,
        scheduleId = 10,
        productIconImg = 1.png,
        courseIntroduce = 小学一年级上册第1章
    }*/


    /*







    "stateText": "未上课"



     */


    var productId = ""
    var courseId = ""
    var orderId = ""
    var scheduleId = ""
    var courseName = ""
    var state = ""
    var productName = ""
    var productIconImg = ""
    var teacherHeadImg = ""
    var teacherName = ""
    var countDownStudySeconds = 0L
    var courseIntroduce = ""


    var studyDatetime = ""
    var teacherEnName = ""

    /**
     * UNTEACH
     */
    var stateValue = ""

    /**
     * eg: 未上课
     */
    var stateText = ""

    var lengthOfCourse = 0L


    var liveRoomNo = ""
    var liveRoomStudentCode = "jdmp8b"



}