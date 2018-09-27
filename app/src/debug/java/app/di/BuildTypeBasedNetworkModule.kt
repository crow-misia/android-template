package app.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import javax.inject.Singleton

@Module internal class BuildTypeBasedNetworkModule {
    @Singleton @Provides @IntoSet
    fun provideStetho(): Interceptor = StethoInterceptor()
}