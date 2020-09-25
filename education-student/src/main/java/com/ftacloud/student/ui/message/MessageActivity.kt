package com.ftacloud.student.ui.message

import android.view.ViewGroup
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.list.BaseRefreshListActivity
import com.ftacloud.student.frames.entity.Message
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.CommonUtils

/**
 * Created by Wangsw on 2020/9/25 0025 14:00.
 * </br>
 *  消息
 *
 */
class MessageActivity : BaseRefreshListActivity<Message, MessagePresenter>(), MessageView  {

    override fun getMainTitle() = R.string.message_title

    override fun initViews() {
        super.initViews()
        CommonUtils.hasNotificationPermission(this, false)
    }

    override fun getRecyclerAdapter(): RecyclerArrayAdapter<Message> {
        val adapter = object : RecyclerArrayAdapter<Message>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Message> {

                val holder = MessageHolder(parent)

                return holder
            }

        }

        adapter.setOnItemClickListener {

        }
        return adapter
    }
}