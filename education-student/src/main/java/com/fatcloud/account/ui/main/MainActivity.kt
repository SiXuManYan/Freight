package com.fatcloud.account.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.flyco.tablayout.listener.OnTabSelectListener
import com.fatcloud.account.R
import com.fatcloud.account.common.OssUtil
import com.fatcloud.account.common.StudentUtil
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.storage.entity.User
import com.fatcloud.account.ui.account.register.RegisterActivity
import com.fatcloud.account.ui.app.CloudAccountApplication
import com.fatcloud.account.ui.course.my.MyCourseActivity
import com.fatcloud.account.ui.course.my.pad.MyCoursePadFragment
import com.fatcloud.account.ui.course.schedule.ClassScheduleActivity
import com.fatcloud.account.ui.course.schedule.pad.ClassSchedulePadFragment
import com.fatcloud.account.ui.home.HomeFragment
import com.fatcloud.account.ui.message.MessageActivity
import com.fatcloud.account.ui.order.list.OrderActivity
import com.fatcloud.account.ui.settings.SettingActivity
import com.fatcloud.account.ui.settings.child.WebFragment
import com.fatcloud.account.ui.task.lists.TaskContainerActivity
import com.fatcloud.account.ui.task.lists.frgm.TaskFragment
import com.fatcloud.account.ui.tests.my.MyTestActivity
import com.fatcloud.account.ui.user.UserActivity
import com.sugar.library.event.ImageUploadEvent
import com.sugar.library.ui.widget.CircleImageView
import com.sugar.library.ui.widget.pagefilp.SampleActivity
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.pager
import kotlinx.android.synthetic.main.activity_main.tabs_type
import kotlinx.android.synthetic.main.app_bar_main.*

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
                Constants.EVENT_UPDATE_PUSH_DEVICE_ID -> {
                    presenter.updatePushDeviceId(this, it.content!!)
                }
                Constants.EVENT_UPDATE_USER_INFO -> {
                    initHeaderView()
                }
                else -> {
                }
            }
        })

        presenter.subsribeEventEntity<ImageUploadEvent>(Consumer {
            if (it.formWhichClass == UserActivity::class.java) {
                OssUtil.getRealOssUrl(this, it.finalUrl, object : CloudAccountApplication.OssSignCallBack {
                    override fun ossUrlSignEnd(url: String) {

                        avatar_iv?.let {
                            Glide.with(this@MainActivity).load(url).into(it)
                        }

                        my_iv?.let {
                            Glide.with(this@MainActivity).load(url).into(it)
                        }

                    }
                })
            }
        })
    }

    private fun initHeaderView() {

        val header = nav_view?.getHeaderView(0)
        if (header == null) {
            return
        }

        val user = User.get()
        val avatar = header.findViewById<CircleImageView>(R.id.avatar_iv)
        if (user.headImg.isNotBlank()) {
            OssUtil.getRealOssUrl(this, user.headImg, object : CloudAccountApplication.OssSignCallBack {
                override fun ossUrlSignEnd(url: String) {
                    Glide.with(this@MainActivity).load(url).into(avatar)
                }
            })
        }
        avatar.setOnClickListener {
            startActivity(UserActivity::class.java)
        }

        header.findViewById<TextView>(R.id.user_name_tv).text = user.name
        header.findViewById<TextView>(R.id.basic_tv).text = getString(R.string.english_level_format, user.stageValue)
        header.findViewById<RelativeLayout>(R.id.menu_my_course).setOnClickListener {
            startActivity(MyCourseActivity::class.java)
        }

        header.findViewById<RelativeLayout>(R.id.menu_class_schedule).setOnClickListener {
            startActivity(ClassScheduleActivity::class.java)
        }

        header.findViewById<RelativeLayout>(R.id.menu_task).setOnClickListener {
            startActivity(TaskContainerActivity::class.java)
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

        val photo = findViewById<ImageView>(R.id.my_iv)
        val headImg = User.get().headImg
        if (headImg.isNotBlank()) {
            OssUtil.getRealOssUrl(this, headImg, object : CloudAccountApplication.OssSignCallBack {
                override fun ossUrlSignEnd(url: String) {
                    Glide.with(this@MainActivity).load(url).into(photo)
                }
            })

        }

        photo.setOnClickListener {
            val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
            drawer?.open()
        }
        findViewById<View>(R.id.message_iv).setOnClickListener {
            startActivity(MessageActivity::class.java)
        }
    }

    /**
     *
     */
    override fun initPadLayout() {
        pager?.adapter = PagerAdapter(supportFragmentManager)
        pager?.offscreenPageLimit = TAB_TITLES.size
        pager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) = Unit

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                tabs_type?.currentTab = position
                content_switcher?.displayedChild = 0

            }

        })
        tabs_type?.setViewPager(pager, TAB_TITLES.toTypedArray())
        tabs_type?.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                pager?.currentItem = position
            }

            override fun onTabReselect(position: Int) {

            }

        })

        avatar_iv?.setOnClickListener {
            content_switcher?.displayedChild = 1
        }

        course_ll?.setOnClickListener {
            startActivity(MyCourseActivity::class.java)
        }
        course_table_ll?.setOnClickListener {
            startActivity(ClassScheduleActivity::class.java)
        }
        task_ll?.setOnClickListener {
            startActivity(TaskContainerActivity::class.java)
        }
        order_ll?.setOnClickListener {
            startActivity(OrderActivity::class.java)
        }

        test_ll?.setOnClickListener {
            startActivity(MyTestActivity::class.java)
        }
        service_ll?.setOnClickListener {
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Constants.CONSUMER_HOT_LINE)))
        }
        message_ll?.setOnClickListener {
            startActivity(MessageActivity::class.java)
        }

        CommonUtils.getFriendlyTime(welcome_tv)

    }


    internal class PagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> {
                    return HomeFragment()
                }
                1 -> {
                    return MyCoursePadFragment()
                }
                2 -> {
                    return ClassSchedulePadFragment()
                }
                3 -> {
                    return TaskFragment()
                }
                else -> {
                    return WebFragment.newInstance("")
                }
            }
        }

        override fun getCount() = TAB_TITLES.size
    }

    override fun unReadMessageCount(it: String) {

        if (StudentUtil.getSafeIntFormString(it) > 0) {
            message_count_tv?.text = getString(R.string.add_format, it)
            message_count_tv?.visibility = View.VISIBLE
        } else {
            message_count_tv?.visibility = View.GONE
        }


    }


}
