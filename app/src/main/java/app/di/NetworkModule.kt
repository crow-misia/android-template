package app.di

import app.data.ApplicationJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [ BuildTypeBasedNetworkModule::class ])
open class NetworkModule {
    @Singleton @Provides
    fun provideOkHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient =
            OkHttpClient.Builder().apply {
                interceptors.forEach {
                    addInterceptor(it)
                }
                followRedirects(false)
            }.build()

    @Singleton @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
            .add(ApplicationJsonAdapterFactory.INSTANCE)
            .build()

    @Singleton @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
}
