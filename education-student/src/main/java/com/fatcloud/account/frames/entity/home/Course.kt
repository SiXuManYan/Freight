package com.fatcloud.account.frames.entity.home

/**
 * Created by Wangsw on 2020/10/12 0012 19:07.
 * </br>
 *
 */
open class Course {

    var id = ""
    var productId = ""
    var productName = ""
    var productIntroduce = ""


    /**
     * @see CourseProductType.EXPERIENCE  体验课
     * @see CourseProductType.BUDDY     教学课(订单课)
     * @see CourseProductType.TEACHING    陪读课(订单课)
     */
    var productType = ""
    var productTags = ArrayList<String>()
    var productMoney = ""
    var productMoneyOfDiscount = ""

    /**
     *  @see CourseState.UNACTIVE         未激活
     *  @see CourseState.UNTEACH          未上课
     *  @see CourseState.TAUGHT           已结束
     *  @see CourseState.TEACHING         上课中
     *  @see CourseState.TEACHING         上课中
     *
     *  UNTEACH-未上课
     */
    var state = ""

    /**
     * UNTEACH
     */
    var stateValue = ""

    /**
     * 年月日时分秒
     * 用于插入课程表
     */
    var studyDatetime = ""

    /**
     * 倒计时3
     */
    var countDownToStudyTimeSeconds = "0"


//  ##################################


    /**
     * 课程图片
     */
    var productIconImg: String = ""

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


    //#################



}