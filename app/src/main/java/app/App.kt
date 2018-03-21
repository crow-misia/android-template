package app

import android.support.v7.app.AppCompatDelegate
import app.di.DaggerAppComponent
import app.util.secure.PRNGFixes
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class App : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        setupSecureRandom()
        setupVectorDawable()
        setupThreeTenABP()
    }

    private fun setupSecureRandom() {
        PRNGFixes.apply()
    }

    private fun setupVectorDawable() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    open fun setupThreeTenABP() {
        AndroidThreeTen.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .build()
    }
}
