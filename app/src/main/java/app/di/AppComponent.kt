package app.di

import android.app.Application
import app.App
import app.di.activitymodule.MainActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    ViewModelModule::class,
    MainActivityBuilder::class
])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun build(): AppComponent
    }

    override fun inject(app: App)
}