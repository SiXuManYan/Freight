package com.fatcloud.account.frames.entity

import java.io.Serializable

/**
 * Created by Wangsw on 2020/11/2 0002 19:24.
 * </br>
 *
 */
class FormalCourseDetail :Serializable {

/*
    "data": {
        "teacherHeadImg": "dev/readingkids/account/teacher/1334054772856197120/img-header/20201202-164042-0307_account-header2.png",
        "teacherName": "郑兆辉",
        "teacherIntroduce": "dev/readingkids/account/teacher/1334054772856197120/img-introduce/20201202-164047-0581_account-introduce.png",
        "productId": "1326805181085650944",
        "liveRoomNo": "20120265312076",
        "liveRoomStudentCode": "ddta73",
        "scheduleOuts": [
            {
                "courseName": "体验课程：测试勿用勿删",
                "lengthOfCourse": 15,
                "scheduleId": "1334297365171015680",
                "studyDatetime": "2020-12-03 10:00:00",
                "countDownStudySeconds": "-940",
                "scheduleState": "UNTEACH-未上课",
                "scheduleStateValue": "UNTEACH",
                "scheduleStateText": "未上课"
            }
                        ]
    }
    */

    var teacherHeadImg = ""

    var teacherName = ""

    var teacherIntroduce = ""

    var productIntroduce = ""

    var productName = ""

    var productId = ""

    var liveRoomNo = ""
    var liveRoomStudentCode = ""


    var scheduleOuts = ArrayList<ScheduleOut>()


    class ScheduleOut :Serializable {
        var courseName: String = ""
        var lengthOfCourse = ""
        var courseId = ""
        var scheduleId = ""
        var studyDatetime = ""
        var scheduleState = ""
        var courseDetailImgs = ""
    }

}