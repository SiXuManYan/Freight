package com.sugar.library.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.View

import com.blankj.utilcode.util.*
import com.sugar.library.R
import com.sugar.library.ui.widget.dialog.AlertDialog

import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Wangsw on 2020/6/12 0012 17:29.
 * </br>
 * 产品相关逻辑处理
 */
object ProductUtils {


    //Reactive收集
    private var compositeDisposable: CompositeDisposable? = null


    /**
     * 处理经营范围pid
     *  ArrayList<String> ->  ArrayList<Int>
     */
    fun stringList2IntList(selectPid: ArrayList<String>): ArrayList<Int> {
        val scope: ArrayList<Int> = ArrayList()
        selectPid.forEach {
            scope.add(it.toInt())
        }
        return scope
    }


    /**
     * 添加订阅
     * @param subscription 订阅
     */
    fun addSubscribe(subscription: Disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = CompositeDisposable()
        }
        compositeDisposable?.add(subscription)
    }

    /**
     * 相册权限申请
     */
    fun requestAlbumPermissions(activity: Activity?): Boolean {
        var isGranted = false

        activity?.let {
            addSubscribe(
                RxPermissions(it)
                    .request(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    .subscribe {
                        isGranted = true
                    })
        }

        return isGranted
    }


    fun showPermissionFailure(context: Context?, message: String) {
        AlertDialog.Builder(context).setTitle(StringUtils.getString(R.string.utils_dialog_hint))
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(R.string.utils_dialog_yes, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
                AppUtils.launchAppDetailsSettings()
            })
            .setNegativeButton(R.string.utils_dialog_no, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
            .create()
            .show()
    }

    fun getEditValueToBigDecimal(editValue: String): BigDecimal {
        return if (editValue.isNullOrBlank()) {
            BigDecimal.ZERO
        } else {
            try {
                BigDecimal(BigDecimal(editValue).stripTrailingZeros().toPlainString())
            } catch (e: Exception) {
                BigDecimal.ZERO
            }
        }
    }


    /**
     * 验证 银行卡号
     */
    fun isBankCardNumber(cardString: String, typeString: String? = ""): Boolean {


        // 16,17, 19 位数
        val match = AndroidUtil.checkBankCardNumber(cardString)


        if (!match) {
            ToastUtils.showShort(StringUtils.getString(R.string.bank_number_wrong_format, typeString))
        }

        return match
    }


    /**
     * 验证 手机号
     */
    fun isPhoneNumber(string: String, typeString: String? = ""): Boolean {

        if (string.isEmpty()) {
            ToastUtils.showShort(StringUtils.getString(R.string.phone_number_empty_format,typeString))
            return false
        }

        val match = AndroidUtil.isMobileNumber(string)
        if (!match) {
            ToastUtils.showShort(StringUtils.getString(R.string.phone_number_wrong_format, typeString))
        }
        return match
    }


    /**
     * 非空检验
     */
    fun checkEmpty(string: String, typeString: String? = ""): Boolean {

        if (string.isEmpty()) {
            ToastUtils.showShort(StringUtils.getString(R.string.text_empty_format,typeString))
            return false
        }
        return true


    }


    /**
     * 验证 身份证号
     * 支持1/2代(15位/18位数字)
     */
    fun isIdCardNumber(string: String, typeString: String? = ""): Boolean {
        val match = RegexUtils.isMatch(
            "(^\\d{8}(0\\d|10|11|12)([0-2]\\d|30|31)\\d{3}\$)|(^\\d{6}(18|19|20)\\d{2}(0[1-9]|10|11|12)([0-2]\\d|30|31)\\d{3}(\\d|X|x)\$)",
            string
        )
        if (!match) {
            ToastUtils.showShort(StringUtils.getString(R.string.id_card_number_wrong_format, typeString))
        }
        return match
    }

    /**
     * 大于三个字的中文
     */
    fun isThreeChineseName(string: String, typeString: String? = ""): Boolean {

        if (string.isBlank()) {
            ToastUtils.showShort(StringUtils.getString(R.string.input_format, typeString))
            return false
        }

        if (RegexUtils.isMatch("^[a-zA-Z]+\$", string) || string.length < 3 || string.length > 30) {
            ToastUtils.showShort(typeString + "请输入3~30个中文")
            return false
        }
        return true
    }


    /**
     * 大于三个字的中文
     */
    fun is18TaxNumber(string: String): Boolean {

        if (string.length != 18) {
            ToastUtils.showShort("请输入18位纳税人识别号")
            return false
        }
        return true
    }


    fun handleDoubleClick(view: View) {
        view.isClickable = false
        view.postDelayed({
            view.isClickable = true
        }, 300)
    }


/*    fun lookGallery(context: Context, url: String) {
        if (url.isBlank()) {
            return
        }
        val imageList = ArrayList<String>()
        imageList.add(url)
        val bundle = Bundle().apply {
            putStringArrayList(Constants.PARAM_LIST, imageList)
            putInt(Constants.PARAM_INDEX, 0)
        }
        context.startActivity(Intent(context, GalleryActivity::class.java).putExtras(bundle))
    }*/


    /**
     * edit text  仅支持中文输入
     *
    fun onlySupportChineseInput(vararg editTextList: EditText) {
    editTextList.forEach {
    it.addTextChangedListener(LimitInputTextWatcher(it))
    }
    }
     */


    fun hasDuplicateName(nameArrays: List<String>? = ArrayList(), vararg names: String): Boolean {


        val stringList = ArrayList<String>(names.asList())
        nameArrays?.let {
            stringList.addAll(it)
        }
        val stringSet: Set<String> = HashSet(stringList)
        return if (stringList.size == stringSet.size) {
            false
        } else {
            ToastUtils.showShort("姓名不能重复")
            true
        }

    }


    /**
     * 检查重复姓名
     */
    fun hasDuplicateName(vararg names: String): Boolean {

        val stringList = ArrayList<String>(names.asList())
        val stringSet: Set<String> = HashSet(stringList)

        return if (stringList.size == stringSet.size) {
            false
        } else {
            ToastUtils.showShort("姓名不能重复")
            true
        }
    }


}