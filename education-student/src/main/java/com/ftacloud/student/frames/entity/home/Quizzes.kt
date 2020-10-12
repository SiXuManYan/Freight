package com.ftacloud.student.frames.entity.home

/**
 * Created by Wangsw on 2020/10/12 0012 19:08.
 * </br>
 *
 */
class Quizzes {

    var id = ""
    var name = ""
    var introduce = ""
    var iconImg = ""

    /**
     * @see QuizzesState.UNSUBMITTED 未提交
     * @see QuizzesState.REVIEWED    已提交
     * @see QuizzesState.REVIEWING   评分中
     * @see QuizzesState.DONE        已查看
     */
    var state = ""
}