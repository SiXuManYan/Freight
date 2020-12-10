package com.fatcloud.account.ui.task.reserve

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.fatcloud.account.R
import com.fatcloud.account.common.OssUtil
import com.fatcloud.account.frames.entity.Buddy
import com.fatcloud.account.frames.event.ReserveEvent
import com.fatcloud.account.ui.app.CloudAccountApplication
import com.sugar.library.event.RxBus
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_reserve.*


/**
 * Created by Wangsw on 2020/7/30 0030 16:13.
 * </br>
 *
 */
class ReserveHolder(parent: ViewGroup?) : BaseItemViewHolder<Buddy>(parent, R.layout.item_reserve), LayoutContainer {

    override val containerView: View? get() = itemView

    var showTitle = false


    override fun setData(data: Buddy?) {
        if (data == null) {
            return
        }

        OssUtil.getRealOssUrl(context, data.buddyHeadImg, object : CloudAccountApplication.OssSignCallBack {
            override fun ossUrlSignEnd(url: String) {
                Glide.with(context).load(url).into(tag_iv)
            }
        })

        title_tv.text = data.buddyName
        sex_tv.text = if (data.sexText == context.getString(R.string.boy)) {
            "♂"
        } else {
            "♀"
        }
        age_tv.text = data.age
        reserve_tv.setOnClickListener {
            if (data.buddyTimeOuts.isNotEmpty()) {
                val time = ArrayList<String>()

                data.buddyTimeOuts.forEach {
                    time.add(it.buddyDatetime)
                }
                showSingleChoiceDialog(time.toTypedArray(), data.buddyTimeOuts)
            }
        }
    }


    var yourChoice = -1
    private fun showSingleChoiceDialog(items: Array<String>, buddyTimeOuts: ArrayList<Buddy.BuddyTime>) {
        yourChoice = -1
        val singleChoiceDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        singleChoiceDialog.setTitle(context.getString(R.string.select_time))
        singleChoiceDialog.setSingleChoiceItems(items, 0) { _, which ->
            yourChoice = which
        }
        singleChoiceDialog.setPositiveButton(R.string.confirm) { _, _ ->
            if (yourChoice != -1) {
                ToastUtils.showShort(context.getString(R.string.your_choice) + items[yourChoice], Toast.LENGTH_SHORT)
                RxBus.post(ReserveEvent(buddyTimeOuts[yourChoice]))
            }
        }
        singleChoiceDialog.show()
    }


}