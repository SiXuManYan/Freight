package com.fatcloud.account.ui.task.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import butterknife.OnClick
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.R
import com.fatcloud.account.common.StudentConstants.PARAM_TASK_OF_COURSE_ID
import com.fatcloud.account.common.download.DemoUtil
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.frames.entity.FormalCourseDetail
import com.fatcloud.account.frames.entity.TaskDetail
import com.fatcloud.account.ui.course.detail.prepare.child.directory.DirectoryHolder
import com.fatcloud.account.ui.task.reserve.ReserveListActivity
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.liulishuo.filedownloader.BaseDownloadTask
import com.liulishuo.filedownloader.FileDownloadListener
import com.liulishuo.filedownloader.FileDownloader
import com.sugar.library.util.CommonUtils
import kotlinx.android.synthetic.main.activity_after_class_task.*
import kotlinx.android.synthetic.main.directory.*
import kotlinx.android.synthetic.main.item_reading_task_container_child.*
import java.io.File

/**
 * Created by Wangsw on 2020/9/27 0027 13:43.
 * </br>
 *  课后任务详情
 */
class TaskDetailActivity : BaseMVPActivity<TaskDetailPresenter>(), TaskDetailView {

    private var downLoadUrl = ""

    private lateinit var recyclerAdapter: RecyclerArrayAdapter<TaskDetail.ReadingBookOut>

    private var selectSet = HashSet<TaskDetail.ReadingBookOut>()


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

        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

    }

    private fun initExtra() {
        if (intent.extras == null || !intent.extras!!.containsKey(PARAM_TASK_OF_COURSE_ID)) {
            finish()
            return
        }
        val courseId = intent.extras!!.getString(PARAM_TASK_OF_COURSE_ID)
        presenter.loadDetail(this, courseId)
    }

    override fun bindDetail(it: TaskDetail) {
        task_name_tv.text = it.native_name
        remaining_tv.text = getString(R.string.remaining_appointments, it.native_num)
        downLoadUrl = it.exerciseUrl
        recyclerAdapter.addAll(it.readingBookOuts)
    }

    fun getRecyclerAdapter(): RecyclerArrayAdapter<TaskDetail.ReadingBookOut> {

        val adapter = object : RecyclerArrayAdapter<TaskDetail.ReadingBookOut>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskDetailHolder {
                val holder = TaskDetailHolder(parent)

                holder.check_view.setOnCheckedChangeListener { buttonView, isChecked ->
                    val model = recyclerAdapter.allData[holder.adapterPosition]
                    model.nativeSelect = isChecked
                    recyclerAdapter.notifyItemChanged(holder.adapterPosition)
                    if (isChecked) {
                        selectSet.add(model)
                    } else {
                        selectSet.remove(model)
                    }
                }
                return holder
            }
        }
        return adapter
    }


    @OnClick(
        R.id.download_tv,
        R.id.upload_tv,
        R.id.book
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.download_tv -> {
                downLoadFile()
            }
            R.id.upload_tv -> {

            }
            R.id.book -> {
                doBook()
            }
            else -> {
            }
        }
    }


    private fun downLoadFile() {
        if (downLoadUrl.isNotBlank()) {
            FileDownloader.getImpl().create(downLoadUrl)
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


    private fun doBook() {

        if (selectSet.isNotEmpty()) {
            startActivity(ReserveListActivity::class.java)
        }


    }


}