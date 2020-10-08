package com.ftacloud.student.common

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import com.ftacloud.student.R

/**
 * Created by Wangsw on 2020/10/8 0008 9:50.
 * </br>
 *
 */
object StudentUtil {


    fun editOpen(editText: EditText, imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_login_eye_open)
        editText.transformationMethod = HideReturnsTransformationMethod.getInstance();
        editText.setSelection(editText.text.toString().length)
    }


    fun editDismiss(editText: EditText, imageView: ImageView) {
        imageView.setImageResource(R.drawable.ic_login_eye_close)
        editText.transformationMethod = PasswordTransformationMethod.getInstance();
        editText.setSelection(editText.text.toString().length)
    }

}