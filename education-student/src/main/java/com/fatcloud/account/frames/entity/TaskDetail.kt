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
    val exerciseUrl: String = ""

    /**
     * 名字
     */
    val native_name: String = ""

    /**
     * 剩余预约次数
     */
    val native_num = 0

    /**
     *  状态
     */
    var native_state = 0

    /**
     * 读书任务
     */
    var readingBookOuts = ArrayList<ReadingBookOut>()

    class ReadingBookOut {
        var readingBookId: Long = 0
        var iconImg: String = ""
        var name: String = ""
        var nativeSelect  = false
    }


}