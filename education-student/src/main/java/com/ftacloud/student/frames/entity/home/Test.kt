package com.ftacloud.student.frames.entity.home

/**
 * Created by Wangsw on 2020/10/12 0012 19:08.
 * </br>
 * 基础测验
 */
class Test {

    var id = ""
    var quizzesId = ""
    var name = ""
    var introduce = ""
    var iconImg = ""

    /**
     * @see TestState.UNSUBMITTED 未提交
     * @see TestState.REVIEWED    已提交
     * @see TestState.REVIEWING   评分中
     * @see TestState.DONE        已评分
     */
    var state = ""
}