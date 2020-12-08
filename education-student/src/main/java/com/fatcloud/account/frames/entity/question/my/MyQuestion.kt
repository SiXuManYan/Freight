package com.fatcloud.account.frames.entity.question.my

class MyQuestion {
    var quizzes :MyQuizzes? = null
    var student :MyStudent? = null

    /**
     * UNSUBMITTED 未提交
     */
    var stateValue  = ""
}

class MyQuizzes {
    var id = ""
    var name = ""
    var introduce = ""

    /**
     * S0
     */
    var stageValue = ""

}

class MyStudent {
    var id = ""
}
