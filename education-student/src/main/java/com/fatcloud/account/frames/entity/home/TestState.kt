package com.fatcloud.account.frames.entity.home

/**
 * Created by Wangsw on 2020/10/12 0012 18:49.
 * </br>
 *  基础测验类型
 */
enum class TestState {

    /** 未提交 */
    UNSUBMITTED,

    /** 已提交 */
    REVIEWED,

    /** 评分中 */
    REVIEWING,

    /** 已评分 */
    DONE

}