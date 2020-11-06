package com.ftacloud.student.frames.entity

/**
 * Created by Wangsw on 2020/10/16 0016 11:02.
 * </br>
 *  课程详情
 */
class CourseDetail {

    var scheduleId: String = ""

    var courseId: String = ""

    var teacherId: String = ""

    var teacherName: String = ""

    var teacherHeadImg: String = ""

    var teacherIntroduce: String = ""

    var studentCount: String = ""

    var courseDetailImgs = ArrayList<String>()

    var studentHeadImgs = ArrayList<String>()

    var countdownEndTimeSeconds: String = ""


    var payingMoney: String = ""


    // 订单
    var orderId: String = ""
    var productId: String = ""
    var productName: String = ""
    var productMoney: String = ""


/*
    {
        "orderId": "1324531460857794560",
        "productId": "1324173557198098432",
        "productName": "小学一年级上（语文）",
        "studentCount": "10",
        "teacherName": "夏洛",
        "teacherHeadImg": "dev/teacher/ios/20201103091643702.png",
        "payingMoney": 10000,
        "productMoney": 12000
    }
*/


}