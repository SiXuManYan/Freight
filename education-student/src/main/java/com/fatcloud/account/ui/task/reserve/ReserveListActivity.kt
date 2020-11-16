package com.fatcloud.account.ui.task.reserve

import android.view.ViewGroup
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.list.BaseRefreshListActivity
import com.fatcloud.account.frames.entity.Buddy
import com.fatcloud.account.frames.event.ReserveEvent
import com.fatcloud.account.ui.task.progress.BookProgressActivity
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.util.Constants
import io.reactivex.functions.Consumer

/**
 * Created by Wangsw on 2020/11/2 0002 11:52.
 * </br>
 *
 */
class ReserveListActivity : BaseRefreshListActivity<Buddy, ReserveListPresenter>(), ReserveListView {


    private var scheduleId = ""


    override fun getMainTitle() = R.string.title_buddy


    override fun initViews() {
        super.initViews()

        initExtra()
        initEvent()

    }

    private fun initExtra() {
        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_SCHEDULE_ID)) {
            finish()
            return
        }
        val scheduleId = intent.extras!!.getString(Constants.PARAM_SCHEDULE_ID)
    }

    private fun initEvent() {
        presenter.subsribeEventEntity(Consumer<ReserveEvent> {
            presenter.book(this, it.result, scheduleId)
        })
    }

    override fun getRecyclerAdapter(): RecyclerArrayAdapter<Buddy> {
        val adapter = object : RecyclerArrayAdapter<Buddy>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Buddy> {
                val holder = ReserveHolder(parent)
                return holder
            }

        }
        return adapter

    }

    override fun bookSuccess() {
        startActivity(BookProgressActivity::class.java)
        RxBus.post(Event(Constants.EVENT_BOOK_SUCCESS))
        finish()
    }

}