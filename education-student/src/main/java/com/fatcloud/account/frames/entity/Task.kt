package com.fatcloud.account.frames.entity

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
    var productId =  ""
    var productName: String = ""
    var id  = ""
    var no  = ""
    var productQuantity  = 0

    var payingMoney  = ""
    var productMoney  = ""
    var createAt  = ""
    var state  = ""
    var stateValue  = ""
    var stateText  = ""


/*
    "createAt": "2020-11-12 16:45:10",
    "state": "PAID-已支付",
    "stateValue": "PAID",
    "stateText": "已支付"*/



    // end


    // 首页任务
    var scheduleId: Long = 0
    var courseId: Long = 0
    var courseName = ""


}