package com.ftacloud.student.frames.components.list

import android.text.TextUtils
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import butterknife.BindView
import com.blankj.utilcode.util.SizeUtils

import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.R
import com.ftacloud.student.R2

import com.jude.easyrecyclerview.EasyRecyclerView
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.jude.easyrecyclerview.decoration.DividerDecoration
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.sugar.library.frames.network.response.LibraryBasePresenter
import com.sugar.library.frames.network.response.list.BaseNoJsonListView2
import com.sugar.library.ui.view.refresh.NoMoreItemView
import com.sugar.library.ui.view.refresh.footer.EmptyImageFooter
import com.sugar.library.ui.view.refresh.footer.EmptyLoadingFooter
import com.sugar.library.ui.view.refresh.footer.EmptyRetryFooter
import com.sugar.library.util.CommonUtils
import kotlinx.android.synthetic.main.activity_refresh_list2.*
import java.util.*

/**
 * 列表Fragment基类
 * 可下拉刷新上拉加载
 * emptyView 会覆盖 headerView
 * 使用 emptyFooter 代替 accidentViewView
 */
abstract class BaseRefreshListActivity<T, P : LibraryBasePresenter> : BaseMVPActivity<P>(), BaseNoJsonListView2<T>, OnRefreshLoadMoreListener {


    @BindView(R2.id.parent_container)
    lateinit var parent_container: RelativeLayout

    @BindView(R2.id.swipe)
    lateinit var swipeLayout: SmartRefreshLayout

    @BindView(R2.id.recycler)
    lateinit var easyRecyclerView: EasyRecyclerView


    lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView

    protected var noMoreItemView: NoMoreItemView? = null
    protected var emptyImageFooter: EmptyImageFooter? = null
    protected var emptyRetryFooter: EmptyRetryFooter? = null
    private var emptyLoadingFooter: EmptyLoadingFooter? = null


    private var adapter: RecyclerArrayAdapter<T>? = null

    /**
     * 兼容使用PageNumber方式请求列表
     */
    var page = 0

    /**
     * 请求的分页数量，默认10
     */
    var pageSize = 10

    /**
     * 用于请求下一页的最后一项 item id ,首页可不传
     */
    var lastItemId: String? = null

    protected var disableLoadMoreView = false


    override fun getLayoutId() = R.layout.activity_refresh_list2

    open fun emptyMessage(): String = getString(R.string.library_hint_data_no_found)
    open fun emptyImage() = R.drawable.img_data_no_found


    override fun initViews() {

        // 刷新和加载
        swipeLayout.setOnRefreshLoadMoreListener(this)
        swipeLayout.setEnableLoadMore(false)
        swipeLayout.setEnableAutoLoadMore(true)

        // header 、 footer 空view (footer 代替 emptyView)
        noMoreItemView = NoMoreItemView()
        emptyImageFooter = EmptyImageFooter(context!!)
        emptyImageFooter?.emptyImageResId = emptyImage()
        emptyImageFooter?.emptyText = emptyMessage()

        emptyLoadingFooter = EmptyLoadingFooter(context!!)
        emptyRetryFooter = EmptyRetryFooter(context!!)
        emptyRetryFooter?.accentListener = object : EmptyRetryFooter.AccentRetryClickListener {
            override fun onRetryClick() {
                onRefresh(swipeLayout)
            }
        }

        // 列表
        adapter = getRecyclerAdapter()
        recyclerView = easyRecyclerView.recyclerView
        easyRecyclerView.setLayoutManager(androidx.recyclerview.widget.LinearLayoutManager(context))




        easyRecyclerView.setAdapterWithProgress(adapter)
        easyRecyclerView.showRecycler()
        getItemDecoration()?.let {
            easyRecyclerView.addItemDecoration(it)
        }

        getMainTitle()?.let {
            setMainTitle(it)
            title_bar.setOnClickListener { view ->
                if (CommonUtils.isDoubleClick(view)) {
                    recyclerView.smoothScrollToPosition(0)
                }

            }
        }

        loadOnVisible()
    }

    override fun bindList(list: ArrayList<T>, lastItemId: String?) {
        bindList(list, TextUtils.isEmpty(this.lastItemId), list.size < pageSize)
        this.lastItemId = lastItemId
    }


    override fun bindList(list: ArrayList<T>, isFirstPage: Boolean, last: Boolean) {

        if (isFirstPage) {
            if (swipeLayout.isRefreshing) {
                swipeLayout.finishRefresh(true)
            }
            adapter?.clear()
            // 第一个，空View
            if (adapter?.footerCount!! > 0) {
                adapter?.removeAllFooter()
            }
            if (list.isEmpty()) {
                adapter?.addFooter(emptyImageFooter)
            }
        } else {
            if (swipeLayout.isLoading) {
                if (list.isEmpty()) {
                    swipeLayout.finishLoadMoreWithNoMoreData()  // 加载完毕
                } else {
                    swipeLayout.finishLoadMore()  // 完成加载
                }
            }
        }

        adapter?.addAll(list)

        // 是否可以上拉加载
        swipeLayout.setEnableLoadMore(!last)

        if (!disableLoadMoreView) {
            if (last && adapter?.allData!!.size > 0) {
                if (adapter?.footerCount!! > 0) adapter?.removeAllFooter()
                adapter?.addFooter(noMoreItemView)
            }
        }


    }

    fun loadOnVisible() {
        if (adapter?.footerCount!! > 0) {
            adapter?.removeAllFooter()
        }
        adapter?.addFooter(emptyLoadingFooter)
        onRefresh()
    }


    override fun showError(code: Int, message: String) {
        super.showError(code, message)
        if (swipeLayout.isRefreshing) {
            swipeLayout.finishRefresh(false)
        }
        if (swipeLayout.isLoading) {
            swipeLayout.finishLoadMore(false)
        }
        if (getAdapter()?.allData?.size == 0) {
            getAdapter()?.removeAllFooter()
            getAdapter()?.addFooter(emptyRetryFooter)
        }
    }

    protected fun getAdapter() = adapter


    open fun getItemDecoration(): androidx.recyclerview.widget.RecyclerView.ItemDecoration? {
        val itemDecoration = DividerDecoration(ContextCompat.getColor(context!!, R.color.split_line_color), SizeUtils.dp2px(0.5f), SizeUtils.dp2px(15f), 0)
        itemDecoration.setDrawHeaderFooter(false)
        return itemDecoration
    }

    override fun onRefresh(refreshLayout: RefreshLayout) = onRefresh()

    override fun onLoadMore(refreshLayout: RefreshLayout) = onLoadMore()

    open fun onRefresh() {
        page = 0
        lastItemId = null
        presenter.loadList(this, page)
        presenter.loadList(this, page, pageSize, lastItemId)
    }

    open fun onLoadMore() {
        page++
        presenter.loadList(this, page)
        presenter.loadList(this, page, pageSize, lastItemId)
    }

    abstract fun getMainTitle(): Int?
    abstract fun getRecyclerAdapter(): RecyclerArrayAdapter<T>

}