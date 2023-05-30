package com.devslatam.f1it2_team_31_android.model

import com.devslatam.f1it2_team_31_android.common.entities.UserRegisterRequest
import com.devslatam.f1it2_team_31_android.common.entities.UserRequest
import com.devslatam.f1it2_team_31_android.common.entities.UserResponse

/* 
* Project: f1it2-team-31-android
* From: com.devslatam.f1it2_team_31_android.model
* Create by Ivan Barbosa on 30/05/2023 at 11:46 a. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class RegisterModel {
    private val requestApi = RequestApi()

    suspend fun postUserRegister(userRegisterRequest: UserRegisterRequest): UserResponse =
        requestApi.requestUserRegister(userRegisterRequest)
}