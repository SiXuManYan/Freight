package com.fatcloud.account.ui.home.holder

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.blankj.utilcode.constant.TimeConstants
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.TimeUtils
import com.bumptech.glide.Glide
import com.fatcloud.account.R
import com.fatcloud.account.common.OssUtil
import com.fatcloud.account.frames.entity.home.Course
import com.fatcloud.account.frames.entity.home.NativeClassSchedule
import com.fatcloud.account.ui.app.CloudAccountApplication
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.util.TimeUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_class_schedule.*
import org.jetbrains.anko.collections.forEachWithIndex
import java.util.*

/**
 * Created by Wangsw on 2020/10/13 0013 11:17.
 * </br>
 *  课程表
 */
class ScheduleHolder(parent: ViewGroup?) : BaseItemViewHolder<NativeClassSchedule>(parent, R.layout.item_home_class_schedule), LayoutContainer {

    override val containerView: View? get() = itemView


    var week = TimeUtil.getNext7DayTime()


    /**
     * 上班一到这时候就困，周一更困。前后看了下大家也都在划水
     */
    @SuppressLint("SetTextI18n")
    override fun setData(data: NativeClassSchedule?) {

        if (data == null) {
            return
        }

        val nativeData = data.nativeData
        if (nativeData.isEmpty()) {
            return
        }

        week.forEachWithIndex { index, weekItem ->

            val chineseWeek = TimeUtils.getChineseWeek(weekItem)
            when (index) {
                0 -> {
                    zero_tv.text = "今天"
                    zero_tv.tag = index
                }
                1 -> {
                    one_tv.text = chineseWeek
                    one_tv.tag = index
                }
                2 -> {
                    two_tv.text = chineseWeek
                    two_tv.tag = index
                }
                3 -> {
                    three_tv.text = chineseWeek
                    three_tv.tag = index
                }
                4 -> {
                    four_tv.text = chineseWeek
                    four_tv.tag = index
                }
                5 -> {
                    five_tv.text = chineseWeek
                    five_tv.tag = index
                }
                6 -> {
                    six_tv.text = chineseWeek
                    six_tv.tag = index
                }
            }

            nativeData.forEach {
                val time = TimeUtils.getChineseWeek(it.studyDatetime)
                if (time == chineseWeek) {
                    val textView = week_ll.findViewWithTag<TextView>(index)
                    val formatTimeHM = TimeUtil.getFormatTimeHM(it.studyDatetime)
                    textView.append(StringUtils.getString(R.string.wrap_format, formatTimeHM))

                    if (index == 0) {

                        OssUtil.getRealOssUrl(context,it.teacherHeadImg,object :CloudAccountApplication.OssSignCallBack{
                            override fun ossUrlSignEnd(url: String) {
                                Glide.with(context).load(url).into(avatar_iv)
                            }
                        })
                        teacher_name_tv.text = it.teacherName
                    }


                    textView.setOnClickListener { view ->
                        OssUtil.getRealOssUrl(context,it.teacherHeadImg,object :CloudAccountApplication.OssSignCallBack{
                            override fun ossUrlSignEnd(url: String) {
                                Glide.with(context).load(url).into(avatar_iv)
                            }
                        })
                        teacher_name_tv.text = it.teacherName
                    }
                }

            }

        }

    }

}