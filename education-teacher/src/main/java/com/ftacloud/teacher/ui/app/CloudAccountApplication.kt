package com.ftacloud.teacher.ui.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerApplication

/**
 * Created by Wangsw on 2021/1/12 0012 11:16.
 * </br>
 *
 */
class CloudAccountApplication : DaggerApplication(), HasActivityInjector, Application.ActivityLifecycleCallbacks {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        TODO("Not yet implemented")
    }

    override fun onActivityPaused(activity: Activity?) {
        TODO("Not yet implemented")
    }

    override fun onActivityResumed(activity: Activity?) {
        TODO("Not yet implemented")
    }

    override fun onActivityStarted(activity: Activity?) {
        TODO("Not yet implemented")
    }

    override fun onActivityDestroyed(activity: Activity?) {
        TODO("Not yet implemented")
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onActivityStopped(activity: Activity?) {
        TODO("Not yet implemented")
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }
}