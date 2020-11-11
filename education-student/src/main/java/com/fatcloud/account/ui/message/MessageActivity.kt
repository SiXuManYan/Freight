package com.fatcloud.account.ui.message

import android.view.ViewGroup
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.list.BaseRefreshListActivity
import com.fatcloud.account.frames.entity.Message
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

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
        if (isPad()) {
            recyclerView.layoutManager = androidx.recyclerview.widget.GridLayoutManager(context,2)
        }
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