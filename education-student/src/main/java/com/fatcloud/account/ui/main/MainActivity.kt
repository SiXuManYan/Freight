package com.fatcloud.account.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
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
            if (it.formWhichClass == UserActivity::class.java && avatar_iv != null) {
                OssUtil.getRealOssUrl(this, it.finalUrl, object : CloudAccountApplication.OssSignCallBack {
                    override fun ossUrlSignEnd(url: String) {
                        Glide.with(this@MainActivity).load(url).into(avatar_iv)
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

//            val list  = ArrayList<String>()
//            list.add("https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2870405105,1377151161&fm=26&gp=0.jpg")
//            list.add("https://www.baidu.com/link?url=emD1iKx47T7D_8S_IawfUuZkjps_W4CtXDhil231RLyUx1-E3qlEmvmagRllMZhfZKKTDbp1wihZONbJG4hT9ZuT7LkZ7yjPL2mw_B-PNc34oDXn0WIcGxY0roJDLtefrydgw3dE-iXV9kMzMEaZ-GXyoPoXpd4sNyxdNanz7KWgbJjkoW4RH7Pkl43TfpJujWRdbdhXo3LLgmLfCMpWmKSkbJ4ahxURNvgHDXsSsvsXOpixr0MDObq1yB8i1zoE0VNIDJeXF3vx27DPw9JkkmUUNSGdL-MDPwhxq59c3j9CVhOx4wQQ0Wpmp_BkX0UIA4qZmij3Zss9P4Kt5mldv5GHjNiB95jS0WUmhQ485DeJurAI5Zh7jZOLwUkW4IntJpDbS_tBLiOjmvjIxK2iWCJRaoFqr-EjZ7Mh3k7RJbnUWOp8BZwt8Um9Iml-RlUF6-UeeYjOZaXncUvP-lTTI7P8BJxofiMUnvMmC631zYKvfaLWsqVMLwMEiB9OiRj9kVBMplDgm-xRqea-p5rgjilnFkSQtBDZkDnAiJkmB2hv5VZFBe6YaEvl0jBr5qZ6ajlbUuPuUei1eh8ogTbccVqn_shFAWE1NWtAUQYJUqf6vOPXGRfx7Pz56aiJr0Ldgp7UCsab8K3PPrhO_l2CFa&timg=https%3A%2F%2Fss0.bdstatic.com%2F94oJfD_bAAcT8t7mm9GUKT-xh_%2Ftimg%3Fimage%26quality%3D100%26size%3Db4000_4000%26sec%3D1606463751%26di%3D83f7b65eee5e2e6a0632cb873c986201%26src%3Dhttp%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201604%2F22%2F20160422125942_RiF32.jpeg&click_t=1606463770168&s_info=1641_838&wd=&eqid=8e99dffe0008fedc000000025fc0b106")
//
//            startActivity( SampleActivity::class.java, Bundle().apply {
//                putStringArrayList(Constants.PARAM_IMAGE_URL,list)
//            })

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
