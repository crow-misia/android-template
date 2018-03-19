package app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.tomoima.debot.Debot

class DebotObserver : Application.ActivityLifecycleCallbacks {
    val debot: Debot by lazy {
        Debot.getInstance()
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        debot.allowShake(activity.applicationContext)
    }

    override fun onActivityStarted(activity: Activity) = Unit

    override fun onActivityResumed(activity: Activity) {
        if (activity is FragmentActivity) {
            debot.startSensor(activity)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        debot.stopSensor()
    }

    override fun onActivityStopped(activity: Activity) = Unit

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) = Unit
}
