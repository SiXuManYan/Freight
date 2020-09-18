package com.ftacloud.student.frames.network.response

import android.content.Context
import com.ftacloud.student.frames.network.ApiService
import com.sugar.library.frames.network.response.LibraryBasePresenter
import com.sugar.library.frames.network.response.LibraryBaseView
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/10 0010 13:55.
 * </br>
 *
 */
open class UserLibraryBasePresenter constructor(private var viewLibrary: LibraryBaseView?) : LibraryBasePresenter(viewLibrary) {

    protected lateinit var apiService: ApiService @Inject set

    protected lateinit var appContext: Context @Inject set





}