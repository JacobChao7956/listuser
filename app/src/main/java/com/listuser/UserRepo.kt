package com.listuser

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.listuser.datamodel.User
import kotlinx.coroutines.flow.flow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class UserRepo {
    private var gson: Gson = GsonBuilder().setLenient().create()
    val users = MutableLiveData<MutableList<User>>()

    private val defaultOkHttpClient: OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(60L, TimeUnit.SECONDS)
        readTimeout(60L, TimeUnit.SECONDS)
        writeTimeout(60L, TimeUnit.SECONDS)
        addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        retryOnConnectionFailure(true)
    }.build()

//    private val defaultInterceptor = Interceptor { it.proceed(it.request()) }

    val userApi: UserApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(gson))
            baseUrl("https://api.github.com/")
        }
            .client(defaultOkHttpClient)
            .build()
            .create(UserApi::class.java)
    }

    fun getUser() = flow { emit(userApi.queryUsers()) }
}