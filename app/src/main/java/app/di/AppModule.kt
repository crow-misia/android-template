package app.di

import android.app.Application
import android.content.Context
import app.util.rx.AppSchedulerProvider
import app.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module internal class AppModule {
    @Singleton @Provides
    fun provideContext(application: Application): Context = application

    @Singleton @Provides
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}
