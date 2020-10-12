package com.ftacloud.student.ui.home

import android.view.ViewGroup
import com.ftacloud.student.frames.components.list.BaseRefreshListFragment
import com.ftacloud.student.frames.entity.home.HomeViewType
import com.ftacloud.student.frames.entity.home.Quizzes
import com.ftacloud.student.frames.entity.home.Schedule
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter

/**
 * 首页
 */
class HomeFragment : BaseRefreshListFragment<Any, HomePresenter>(), HomeView {


    override fun getRecyclerAdapter(): RecyclerArrayAdapter<Any> {

        val adapter = object : RecyclerArrayAdapter<Any>(context) {
            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<*> {
//                return if (viewType == Constants.ITEM_POST) {
//                    val recommendViewHolder = RecommendViewHolder2(parent)
//                    recommendViewHolder.onChildViewClickListener = this@RecommendFragment
//                    recommendViewHolder.onMediaPlayListener = this@RecommendFragment
//                    recommendViewHolder
//                } else {
//                    val adViewHolder = AdViewHolder(parent)
//                    adViewHolder.onMediaPlayListener = this@RecommendFragment
//                    adViewHolder
//                }
            }

            override fun getViewType(position: Int): Int {
                val item = getItem(position)


                return when (item) {
                    is Quizzes -> {
                        HomeViewType.Quizzes.ordinal
                    }
                    else -> {
                        HomeViewType.Schedule.ordinal
                    }
                }

            }
        }
    }


}
