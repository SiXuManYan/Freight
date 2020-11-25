package com.fatcloud.account.ui.task.detail

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.R
import com.fatcloud.account.common.download.DemoUtil
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.frames.entity.TaskDetail
import com.fatcloud.account.ui.task.book.BookActivity
import com.fatcloud.account.ui.task.book.lists.BookListActivity
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_after_class_task.*
import java.io.File

/**
 * Created by Wangsw on 2020/9/27 0027 13:43.
 * </br>
 *  课后任务详情
 */
class TaskDetailActivity : BaseMVPActivity<TaskDetailPresenter>(), TaskDetailView {

    private var downLoadUrl = ""

    private lateinit var recyclerAdapter: RecyclerArrayAdapter<TaskDetail.ReadingBookOut>


    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun getLayoutId() = R.layout.activity_after_class_task

    override fun initViews() {
        initRecycleView()
        initExtra()
    }

    private fun initRecycleView() {
        setMainTitle("课后任务详情")
        FileDownloader.setup(this)

        this.recyclerAdapter = getRecyclerAdapter()
        val adapter = recyclerAdapter

        content_rv.layoutManager = LinearLayoutManager(context)
        content_rv.adapter = adapter
        initEvent()
    }

    private fun initEvent() {
        presenter.subsribeEvent(Consumer {
            when (it.code) {
                Constants.EVENT_BOOK_SUCCESS -> {
                    finish()
                }
                else -> {
                }
            }
        })
    }

    private fun initExtra() {
        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_PRODUCT_ID)) {
            finish()
            return
        }
        val productId = intent.extras!!.getString(Constants.PARAM_PRODUCT_ID)
        presenter.loadDetail(this, productId)
    }

    override fun bindDetail(it: TaskDetail) {

        recyclerAdapter.addAll(it.scheduleOuts)
    }

    fun getRecyclerAdapter(): RecyclerArrayAdapter<TaskDetail.ReadingBookOut> {

        val adapter = object : RecyclerArrayAdapter<TaskDetail.ReadingBookOut>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskDetailHolder {
                val holder = TaskDetailHolder(parent)
                return holder
            }
        }
        adapter.setOnItemClickListener {
            val readingBookOut = adapter.allData[it]
            startActivity(BookListActivity::class.java, Bundle().apply {
                putString(Constants.PARAM_COURSE_ID, readingBookOut.courseId)
            })
        }

        return adapter
    }

    private fun downLoadFile() {
        if (downLoadUrl.isNotBlank()) {
            FileDownloader.getImpl()
                .create(downLoadUrl)
                .setPath(DemoUtil.getParentFile(this).path + File.separator + "file" + CommonUtils.getFileSuffix(downLoadUrl))
                .setListener(object : FileDownloadListener() {

                    override fun warn(task: BaseDownloadTask?) = Unit

                    override fun completed(task: BaseDownloadTask?) = ToastUtils.showShort("下载完成")

                    override fun pending(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) = Unit

                    override fun error(task: BaseDownloadTask?, e: Throwable?) = Unit

                    override fun progress(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) = Unit

                    override fun paused(task: BaseDownloadTask?, soFarBytes: Int, totalBytes: Int) = Unit

                }).start()
        }
    }


}