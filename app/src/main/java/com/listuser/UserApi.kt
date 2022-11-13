package com.listuser

import com.listuser.datamodel.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserApi {

    @Headers("Accept: application/vnd.github+json")
    @GET("users")
    suspend fun queryUsers(): Response<MutableList<User>>
}