package com.devslatam.f1it2_team_31_android.model

import com.devslatam.f1it2_team_31_android.common.dataAccess.ApiService
import com.devslatam.f1it2_team_31_android.common.entities.UserRequest
import com.devslatam.f1it2_team_31_android.common.entities.UserResponse
import com.devslatam.f1it2_team_31_android.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* 
* Project: f1it2-team-31-android
* From: com.devslatam.f1it2_team_31_android.model
* Create by Ivan Barbosa on 27/05/2023 at 8:51 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class RequestApi {
    private val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val service = retrofit.create(ApiService::class.java)
    private val dispatcher = Dispatchers.IO

    suspend fun requestUserLogin(userRequest: UserRequest): UserResponse = withContext(dispatcher) {
        service.loginUser(userRequest)
    }
}