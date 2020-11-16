package com.fatcloud.account.frames.entity

/**
 * Created by Wangsw on 2020/11/13 0013 15:09.
 * </br>
 *
 */
open class Buddy {

/*
    {
        "buddyId": "1327095501446844416",
        "buddyName": "机器人2",
        "buddyHeadImg": "dev/teacher/ios/20201103111615478.png",
        "age": 1,
        "sex": "F-女",
        "sexValue": "F",
        "sexText": "女"
    }
    */


    var buddyId = ""
    var buddyName = ""
    var buddyHeadImg = ""

    var age = ""

    /**
     * F-女
     */
    var sex = ""

    /**
     * F
     */
    var sexValue = ""

    /**
     * 女
     */
    var sexText = ""


    var buddyTimeOuts = ArrayList<BuddyTime>()

    /*
   {
        "buddyTimeId": "1326698299700416512",
        "buddyDatetime": "2020-11-14 10:00:00",
        "state": "RELEASED-已发布",
        "stateValue": "RELEASED",
        "stateText": "已发布"
    }
    */


    inner class BuddyTime {
        var buddyTimeId =  ""
        var buddyDatetime =  ""
        var state =  ""
        var stateValue =  ""
        var stateText =  ""

    }


}