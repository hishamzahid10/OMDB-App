package com.hisham.movie.di

import android.content.Context
import android.content.SharedPreferences
import com.hisham.movie.data.prefs.PreferenceManagerImpl
import org.koin.dsl.module

val persistenceDataModule = module {
    single {
        provideSharedPreference(get(), "Movies_prefs")
    }
    single {
        providePreferenceManager(get())
    }
}

fun provideSharedPreference(context: Context, preferenceName: String): SharedPreferences {
    return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
}

fun providePreferenceManager(preferences: SharedPreferences): com.hisham.movie.data.prefs.PreferenceManager =
    PreferenceManagerImpl(preferences)