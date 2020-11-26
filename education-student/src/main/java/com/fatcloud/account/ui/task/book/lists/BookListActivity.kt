package com.fatcloud.account.ui.task.book.lists

import android.os.Bundle
import android.view.ViewGroup
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.list.BaseRefreshListActivity
import com.fatcloud.account.frames.entity.Book
import com.fatcloud.account.ui.task.book.BookDetailActivity
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.Constants

/**
 * Created by Wangsw on 2020/11/24 0024 11:26.
 * </br>
 *  书籍列表
 */
class BookListActivity : BaseRefreshListActivity<Book, BookListPresenter>(), BookListView {


    override fun getMainTitle() = R.string.book_list


    override fun initViews() {
        super.initViews()

        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_COURSE_ID)) {
            finish()
            return
        }
        val courseId = intent.extras!!.getString(Constants.PARAM_COURSE_ID)
        presenter.loadDetail(this, courseId)
    }


    override fun getRecyclerAdapter(): RecyclerArrayAdapter<Book> {

        val adapter = object : RecyclerArrayAdapter<Book>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BookHolder {
                val holder = BookHolder(parent)
                return holder
            }
        }

        adapter.setOnItemClickListener {
            val book = adapter.allData[it]

            startActivity(BookDetailActivity::class.java, Bundle().apply {
                putString(Constants.PARAM_ID, book.buddyBookId)
            })
        }
        return adapter
    }


}