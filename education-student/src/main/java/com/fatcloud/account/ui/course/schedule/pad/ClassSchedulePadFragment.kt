package com.fatcloud.account.ui.course.schedule.pad

import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import com.blankj.utilcode.util.TimeUtils
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.fragment.BaseFragment
import com.fatcloud.account.frames.entity.ClassSchedule
import com.fatcloud.account.frames.entity.MyCourse
import com.fatcloud.account.ui.course.schedule.ClassScheduleHolder
import com.google.android.material.radiobutton.MaterialRadioButton
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.TimeUtil
import kotlinx.android.synthetic.main.fragment_class_schedule_pad.*
import org.jetbrains.anko.collections.forEachWithIndex
import java.util.ArrayList

/**
 * Created by Wangsw on 2020/11/19 0019 13:57.
 * </br>
 *
 */
class ClassSchedulePadFragment : BaseFragment<ClassSchedulePadPresenter>(), ClassSchedulePadView {

    private lateinit var mAdapter: RecyclerArrayAdapter<ClassSchedule>
    private lateinit var nextWeekDates: List<String>
    private lateinit var allData: ArrayList<MyCourse>


    override fun getLayoutId(): Int {
        return R.layout.fragment_class_schedule_pad
    }

    override fun initViews(parent: View) {

        initRecycleView()
        initRadioButton()
    }

    private fun initRecycleView() {
        mAdapter = getRecyclerAdapter()
        rv.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = mAdapter
        }
    }

    override fun loadOnVisible() {
        presenter.loadData(this)
    }

    private fun initRadioButton() {
        date_tv.text = TimeUtils.millis2String(System.currentTimeMillis(), TimeUtils.getSafeDateFormat("yyyy-MM-dd"))

        nextWeekDates = presenter.getNextWeekDates()
        nextWeekDates.forEachWithIndex { index, date ->
            val radioButton = week_rg[index] as MaterialRadioButton
            radioButton.text = TimeUtils.getChineseWeek(date)
            radioButton.tag = date // yyyy-MM-dd HH:mm:ss
        }


        week_rg.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = group.findViewById<MaterialRadioButton>(checkedId)
            val date = radioButton.tag as String
            date_tv.text = TimeUtil.getFormatTimeYMD(date)

            // 删选选中日期对应的数据
            val parentList = ArrayList<ClassSchedule>()
            parentList.clear()
            mAdapter.clear()
            if (allData.isNotEmpty()) {
                mAdapter.addAll(presenter.restoreList(allData,radioButton))
            }

        }

    }

    fun getRecyclerAdapter(): RecyclerArrayAdapter<ClassSchedule> {
        val adapter = object : RecyclerArrayAdapter<ClassSchedule>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<ClassSchedule> {
                val holder = ClassScheduleHolder(parent)
                return holder
            }
        }

        adapter.setOnItemClickListener {

        }
        return adapter
    }


    override fun bindList(list: ArrayList<MyCourse>, lastItemId: String?) {

        if (list.isNotEmpty()) {
            allData = list
            val radioButton = week_rg.findViewById<MaterialRadioButton>(week_rg.checkedRadioButtonId)
            mAdapter.clear()
            mAdapter.addAll(presenter.restoreList(allData, radioButton))
        }


    }

}