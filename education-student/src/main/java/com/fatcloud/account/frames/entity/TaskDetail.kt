package com.fatcloud.account.frames.entity

/**
 * Created by Wangsw on 2020/10/21 0021 13:41.
 * </br>
 *  上传至OSs
 */
class TaskDetail {


    /**
     * 下载地址
     */
    val productId: String = ""




    /**
     * 读书任务
     */
    var scheduleOuts = ArrayList<ReadingBookOut>()

    class ReadingBookOut {

        var iconImg: String = ""

        var scheduleId =  ""

        /**
         * UNACTIVE-未激活
         */
        var scheduleState =  ""

        /**
         * UNACTIVE
         */
        var scheduleStateValue =  ""

        /**
         * 未激活
         */
        var scheduleStateText =  ""
        var courseName: String = ""
        var lengthOfCourse = 0
        var courseId = ""

        var buddyBookIds =  ArrayList<String>()











    }



/*    "data": {
        "productId": "1326806563331117056",
        "scheduleOuts": [
        {
        }
        ]
    }*/

}