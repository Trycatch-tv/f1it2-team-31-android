package com.devslatam.f1it2_team_31_android.model

import com.devslatam.f1it2_team_31_android.common.entities.UserRequest
import com.devslatam.f1it2_team_31_android.common.entities.UserResponse

/* 
* Project: f1it2-team-31-android
* From: com.devslatam.f1it2_team_31_android.model
* Create by Ivan Barbosa on 27/05/2023 at 8:01 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class LoginModel {
    private val requestApi = RequestApi()

    suspend fun postUserLogin(userRequest: UserRequest): UserResponse =
        requestApi.requestUserLogin(userRequest)
}