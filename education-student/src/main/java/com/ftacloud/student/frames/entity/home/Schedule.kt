package com.ftacloud.student.frames.entity.home

/**
 * Created by Wangsw on 2020/10/12 0012 19:07.
 * </br>
 *
 */
open class Schedule {

    var id = ""
    var courseId = ""
    var courseName = ""
    var courseIntroduce = ""


    /**
     * @see ScheduleProductType.EXPERIENCE  体验课
     * @see ScheduleProductType.READING     教学课(订单课)
     * @see ScheduleProductType.TEACHING    陪读课(订单课)
     */
    var productType = ""
    var productTags = ArrayList<String>()
    var productMoney = ""
    var productMoneyOfDiscount = ""

    /**
     *  @see ScheduleState.UNACTIVE         未激活
     *  @see ScheduleState.UNTEACH          未上课
     *  @see ScheduleState.TAUGHT           已结束
     *  @see ScheduleState.TEACHING         上课中
     */
    var state = ""

    /**
     * 年月日时分秒
     * 用于插入课程表
     */
    var studyDatetime = ""

    /**
     * 倒计时
     */
    var countDownToStudyTimeSeconds  = 0L


//  ##################################


    /**
     * 课程图片
     */
    var courseIconImg: String = ""

    /**
     * 教师id
     */
    var teacherId: Long = 0

    /**
     * 教师名字
     */
    var teacherName: String = ""

    /**
     * 教师头像
     */
    var teacherHeadImg: String = ""

    /**
     * 课程直播房间号
     */
    var liveRoomNo: String = ""




}