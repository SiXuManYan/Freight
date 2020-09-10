package com.ftacloud.freightuser.frames.backstage

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.sugar.library.util.Constants


class DataWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams), DataWorkView {

    val presenter = DataWorkPresenter(this)


    override fun showError(code: Int, message: String) = Unit


    override fun doWork(): Result {

        when (inputData.getInt(Constants.ACTION_DATA_WORK, 0)) {
            // 处理具体耗时逻辑
            Constants.ACTION_SYNC -> {


            }



            else -> {

            }

        }
        return Result.success()
    }





}