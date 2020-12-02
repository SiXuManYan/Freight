package com.fatcloud.account.ui.task.book

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.R
import com.fatcloud.account.common.file.FileDisplayActivity
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.frames.entity.BookDetail
import com.fatcloud.account.frames.entity.TaskDetail
import com.fatcloud.account.ui.gallery.GalleryActivity
import com.fatcloud.account.ui.task.book.lists.BookListActivity
import com.fatcloud.account.ui.task.detail.TaskDetailHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.liulishuo.filedownloader.FileDownloader
import com.sugar.library.ui.widget.pagefilp.SampleActivity
import com.sugar.library.util.Constants
import com.sugar.library.util.PermissionUtils
import kotlinx.android.synthetic.main.activity_after_class_task.*

/**
 * Created by Wangsw on 2020/11/24 0024 10:12.
 * </br>
 *  书籍详情
 */
class BookDetailActivity : BaseMVPActivity<BookDetailPresenter>(), BookDetailView {

    private lateinit var bookId: String
    private lateinit var recyclerAdapter: RecyclerArrayAdapter<String>

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun getLayoutId(): Int {
        return R.layout.activity_book
    }

    override fun initViews() {
        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_ID)) {
            finish()
            return
        }
        bookId = intent.extras!!.getString(Constants.PARAM_ID)!!
        presenter.loadDetail(this, bookId)

        initRecycleView()
    }

    private fun initRecycleView() {
        setMainTitle("课后任务详情")
        FileDownloader.setup(this)

        this.recyclerAdapter = getRecyclerAdapter()
        val adapter = recyclerAdapter

        content_rv.layoutManager = LinearLayoutManager(context)
        content_rv.adapter = adapter
    }


    override fun bindImageDetail(data: BookDetail) {
        if (data.contents.isEmpty()) {
            return
        }
        val bundle = Bundle()
        bundle.putStringArrayList(Constants.PARAM_LIST,  data.contents)
        bundle.putInt(Constants.PARAM_INDEX, 0)
        startActivity(Intent(this, GalleryActivity::class.java).putExtras(bundle))
    }

    override fun bindMediaDetail(data: BookDetail) {

        if (data.contents.isEmpty()) {
            return
        }
        PermissionUtils.permissionAny(
            context, PermissionUtils.OnPermissionCallBack {
                if (it) {
                    FileDisplayActivity.show(this, data.contents[0])
                    finish()
                } else {
                    ToastUtils.showShort("请授权相应权限")
                }

            }, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    fun getRecyclerAdapter(): RecyclerArrayAdapter<String> {

        val adapter = object : RecyclerArrayAdapter<String>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BookDetailHolder {
                val holder = BookDetailHolder(parent)
                return holder
            }
        }
        return adapter
    }


}