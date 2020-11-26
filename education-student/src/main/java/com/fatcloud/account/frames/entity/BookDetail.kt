package com.fatcloud.account.frames.entity

/**
 * Created by Wangsw on 2020/11/26 0026 9:26.
 * </br>
 *
 */
class BookDetail {

    companion object {
        val PPT = "PPT"
        val PDF = "PDF"
        val IMG = "IMG"
        val HTML = "HTML"
    }

/*
    {
        "code": "200",
        "msg": "success",
        "data": {
            "buddyBookId": "1323429427601870848",
            "introduce": "阅读教材介绍：20111103创建",
            "contents": [
            "http://192.168.1.191:52001/api/common/download?path=/files/book/buddybook/1323429427601870848/1323429427601870848_001.pdf"
            ],
            "seq": 1,
            "type": "PDF-PDF",
            "typeValue": "PDF",
            "typeText": "PDF"
            }
    }
    */


    var buddyBookId = ""
    var introduce = ""
    var contents = ArrayList<String>()
    var seq = 0
    var type = ""

    /**
     * @see PPT
     * @see PDF
     * @see IMG
     * @see HTML
     */
    var typeValue = ""
    var typeText = ""


}