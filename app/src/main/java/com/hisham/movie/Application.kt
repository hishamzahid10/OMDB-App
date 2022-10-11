package com.hisham.movie

import android.app.Application
import com.hisham.movie.di.apiModule
import com.hisham.movie.di.persistenceDataModule
import com.hisham.movie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin

class MoviesApp: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MoviesApp)

            androidFileProperties()
            modules(
                listOf(
                    viewModelModule, persistenceDataModule, apiModule
                )
            )
        }

    }

}