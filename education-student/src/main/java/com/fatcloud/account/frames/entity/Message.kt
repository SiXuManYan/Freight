package com.fatcloud.account.frames.entity

/**
 * Created by Wangsw on 2020/9/25 0025 14:00.
 * </br>
 *
 */
class Message {


    var id = ""

    /**
     * 消息头
     */
    var title = ""

    /**
     * 消息内容
     */
    var content = ""


    /**
     * 消息状态
     * READ("已读"), UNREAD("未读");
     */
    var state =  ""


    /**
     * 消息类型
     * SYSTEM("系统消息"), NOTICE("通知消息");
     */
    var type =  ""



//    @AllArgsConstructor
//    enum class Type {
//        SYSTEM("系统消息"), NOTICE("通知消息");
//
//        var text: String? = null
//        override fun toString(): String {
//            return "$name-$text"
//        }
//    }
//
//    @AllArgsConstructor
//    enum class State {
//        READ("已读"), UNREAD("未读");
//
//        var text: String? = null
//        override fun toString(): String {
//            return "$name-$text"
//        }
//    }

}