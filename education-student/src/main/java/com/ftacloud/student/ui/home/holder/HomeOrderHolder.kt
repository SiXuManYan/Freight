package com.ftacloud.student.ui.home.holder

import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.StringUtils
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.home.HomeOrder
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_order.*

/**
 * Created by Wangsw on 2020/10/22 0022 19:40a.
 * </br>
 *  首页订单
 */
class HomeOrderHolder(parent: ViewGroup?) : BaseItemViewHolder<HomeOrder>(parent, R.layout.item_home_order), LayoutContainer {


    override val containerView: View? get() = itemView


    override fun setData(data: HomeOrder?) {

        if (data == null) {
            return
        }


        Glide.with(context).load(data.productIconImg).into(image_iv)
        title_tv.text = data.productName
        content_tv.text = data.productIntroduce

        num_tv.text = StringUtils.getString(R.string.class_number_format, data.quantity)
        money_tv.text = StringUtils.getString(R.string.money_symbol_with_blank, data.productMoney)


    }


}