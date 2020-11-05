package com.ftacloud.student.ui.settings.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ftacloud.student.R
import com.ftacloud.student.ui.order.list.child.OrderChildFragment
import com.sugar.library.util.Constants
import kotlinx.android.synthetic.main.fragment_web.*

/**
 * Created by Wangsw on 2020/11/5 0005 10:56.
 * </br>
 *
 */
class WebFragment : Fragment() {




    companion object {

        /**
         * @param categoryValue tab类别，全部时传空
         */
        fun newInstance(url: String): WebFragment {
            val fragment = WebFragment()
            val args = Bundle()
            args.putString(Constants.PARAM_URL, url)
            fragment.arguments = args
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_web, container, false)!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    private fun initViews(view: View) {
        val url = arguments?.getString(Constants.PARAM_URL, "")
        url?.let {
            js_wb.loadUrl("https://www.baidu.com")
        }
    }



}