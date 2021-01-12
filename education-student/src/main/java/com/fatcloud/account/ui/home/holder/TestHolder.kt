package com.fatcloud.account.ui.home.holder

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.fatcloud.account.R
import com.fatcloud.account.common.OssUtil
import com.fatcloud.account.frames.entity.home.Test
import com.fatcloud.account.ui.app.CloudAccountApplication
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_basic_test.*

/**
 * Created by Wangsw on 2020/10/13 0013 9:11.
 * </br>
 *  首页基础测试
 */
class TestHolder(parent: ViewGroup?) : BaseItemViewHolder<Test>(parent, R.layout.item_home_basic_test), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: Test?) {
        if (data == null) {
            return
        }

        title_tv.text = data.name
        OssUtil.getRealOssUrl(context,data.iconImg,object:CloudAccountApplication.OssSignCallBack{
            override fun ossUrlSignEnd(url: String) {
                Glide.with(context).load(url).into(tag_iv)
            }

        })
        content_tv.text = data.introduce


    }

}