package com.fatcloud.account.frames.entity.request

import com.fatcloud.account.common.StudentConstants

/**
 * Created by Wangsw on 2020/10/20 0020 9:50.
 * </br>
 * 消息列表请求
 *
 */
class BuddyBookingRequest {

    /*

          {
              "buddyTimeId": 0,
              "scheduleId": 0,
              "version": 0
          }

    */


    var buddyTimeId = "0"
    var scheduleId = "0"
    val version = StudentConstants.OS_VERSION
}