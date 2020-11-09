package com.ftacloud.student.ui.task

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseActivity
import kotlinx.android.synthetic.main.activity_task_container.*

/**
 * Created by Wangsw on 2020/11/9 0009 15:17.
 * </br>
 * 课后任务列表
 * 首页和单独公用
 */
class TaskContainerActivity :BaseActivity(){

    override fun getLayoutId() = R.layout.activity_task_container

    override fun initViews() {
        setMainTitle(R.string.after_class_task_title)
        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.onTabSelect(0)
    }







    /**
     * Tab数据适配器
     */
    private inner class FragmentAdapter internal constructor(val fm: FragmentManager) : FragmentPagerAdapter(fm) {

        fun onTabSelect(position: Int) {
            val fragment = instantiateItem(fl_content, position) as Fragment
            setPrimaryItem(fl_content, 0, fragment)
            finishUpdate(fl_content)
        }

        override fun getCount(): Int {
            return 1
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean = (obj as Fragment).view === view

        override fun getItem(position: Int): Fragment {
            return TaskFragment()
        }

    }

}