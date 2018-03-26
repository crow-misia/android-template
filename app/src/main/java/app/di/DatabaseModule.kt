package app.di

import android.app.Application
import android.arch.persistence.room.Room
import app.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module open class DatabaseModule {
    companion object {
        val instance = DatabaseModule()
    }

    @Singleton @Provides
    open fun provideDatabase(app: Application): AppDatabase =
            Room.databaseBuilder(app, AppDatabase::class.java, "app.db")
                    .fallbackToDestructiveMigration()
                    .build()
}
