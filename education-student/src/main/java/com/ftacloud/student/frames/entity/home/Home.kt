package com.ftacloud.student.frames.entity.home


/**
 * Created by Wangsw on 2020/10/12 0012 16:49.
 * </br>
 *
 */
class Home {

/*    "data": {
        "quizzesOfStudentOuts": [

        ],
        "scheduleOuts": [

        ]
    }*/

    /**
     * 学生成绩测验
     */
    var quizzesOfStudentOuts = ArrayList<Test>()

    /**
     * 课程
     */
    var scheduleOuts = ArrayList<Schedule>()

}