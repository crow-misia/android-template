package app.di

import android.support.v7.widget.RecyclerView
import dagger.Module
import dagger.Provides

// Share RecycledViewPool between content fragments of ViewPager.
@Module class RecycledViewPoolModule {
    private val recycledViewPool = RecyclerView.RecycledViewPool()

    @Provides
    fun providesRecycledViewPool(): RecyclerView.RecycledViewPool = recycledViewPool
}
