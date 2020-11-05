package com.ftacloud.student.ui.main

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.RelativeLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.ui.course.my.MyCourseActivity
import com.ftacloud.student.ui.course.schedule.ClassScheduleActivity
import com.ftacloud.student.ui.home.HomeFragment
import com.ftacloud.student.ui.message.MessageActivity
import com.ftacloud.student.ui.order.list.OrderActivity
import com.ftacloud.student.ui.settings.SettingActivity
import com.ftacloud.student.ui.settings.child.WebFragment
import com.ftacloud.student.ui.task.TaskActivity
import com.ftacloud.student.ui.tests.my.MyTestActivity
import com.ftacloud.student.ui.user.UserActivity
import com.sugar.library.ui.view.CircleImageView
import com.sugar.library.util.Constants
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMVPActivity<MainPresenter>(), MainView {


    companion object {
        internal val TAB_TITLES = arrayListOf(
            StringUtils.getString(R.string.home),
            StringUtils.getString(R.string.course),
            StringUtils.getString(R.string.course_table),
            StringUtils.getString(R.string.course_task)
        )
    }


    private var tapTime = 0L

    override fun getLayoutId() = R.layout.activity_main

    override fun initViews() {
        initEvent()
        presenter.loadUserInfo(this)
    }


    private fun initEvent() {
        presenter.subsribeEvent(Consumer {
            when (it.code) {
                Constants.EVENT_LOGOUT -> {
                    finish()
                }
                else -> {
                }
            }
        })
    }

    private fun initHeaderView() {

        val header = nav_view?.getHeaderView(0)
        if (header == null) {
            return
        }
        header.findViewById<CircleImageView>(R.id.avatar_iv).setOnClickListener {
            startActivity(UserActivity::class.java)
        }
        header.findViewById<RelativeLayout>(R.id.menu_my_course).setOnClickListener {
            startActivity(MyCourseActivity::class.java)
        }

        header.findViewById<RelativeLayout>(R.id.menu_class_schedule).setOnClickListener {
            startActivity(ClassScheduleActivity::class.java)
        }

        header.findViewById<RelativeLayout>(R.id.menu_task).setOnClickListener {
            startActivity(TaskActivity::class.java)
        }

        header.findViewById<RelativeLayout>(R.id.menu_my_evaluation).setOnClickListener {
            startActivity(MyTestActivity::class.java)
        }

        header.findViewById<RelativeLayout>(R.id.menu_my_order).setOnClickListener {
            startActivity(OrderActivity::class.java)

        }
        header.findViewById<RelativeLayout>(R.id.menu_my_customer_service).setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Constants.CONSUMER_HOT_LINE)))
        }

        header.findViewById<RelativeLayout>(R.id.menu_my_setting).setOnClickListener {
            startActivity(SettingActivity::class.java)
        }


    }


    override fun onBackPressed() {
        if (System.currentTimeMillis() - tapTime > 2000) {
            ToastUtils.showShort(getString(R.string.main_back_hint))
            tapTime = System.currentTimeMillis()
        } else {
            // 保留应用状态
            moveTaskToBack(false);
        }
    }


    override fun initPhoneLayout() {
        initHeaderView()
        findViewById<View>(R.id.my_iv).setOnClickListener {
            val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
            if (drawer != null) {
                drawer.open()
            }
        }
        findViewById<View>(R.id.message_iv).setOnClickListener {
            startActivity(MessageActivity::class.java)
        }
    }

    override fun initPadLayout() {
        pager?.adapter = PagerAdapter(supportFragmentManager)
        pager?.offscreenPageLimit = TAB_TITLES.size
        tabs_type?.setViewPager(pager, TAB_TITLES.toTypedArray())
    }

    internal class PagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> {
                    return HomeFragment()
                }
                else -> {
                    return WebFragment.newInstance("")
                }
            }
        }

        override fun getCount() = TAB_TITLES.size
    }


}
