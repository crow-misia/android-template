package app.di.activitymodule

import app.di.RecycledViewPoolModule
import app.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module interface MainActivityBuilder {
    @ContributesAndroidInjector(modules = [
        MainActivityModule::class,
        RecycledViewPoolModule::class
    ])
    fun contributeMainActivity(): MainActivity
}
