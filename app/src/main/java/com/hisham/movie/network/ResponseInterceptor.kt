package com.hisham.movie.network

import android.content.Context
import com.hisham.movie.data.prefs.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response


class ResponseInterceptor (context: Context?,preference: PreferenceManager):Interceptor{
    private val applicationContext = context?.applicationContext
    private val preferenceManager = preference
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request);

        // todo deal with the issues the way you need to
        if (response.code == 403) {
            if (applicationContext != null) {
            }
            return response;
        }

        return response;
    }
}