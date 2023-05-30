package com.devslatam.f1it2_team_31_android.common.dataAccess

import com.devslatam.f1it2_team_31_android.common.entities.UserRegisterRequest
import com.devslatam.f1it2_team_31_android.common.entities.UserResponse
import com.devslatam.f1it2_team_31_android.common.entities.UserRequest
import com.devslatam.f1it2_team_31_android.common.utils.Constants
import retrofit2.http.Body
import retrofit2.http.POST

/* 
* Project: f1it2-team-31-android
* From: com.devslatam.f1it2_team_31_android.common.dataAccess
* Create by Ivan Barbosa on 27/05/2023 at 8:26 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
interface ApiService {
    @POST(Constants.API_PATH_LOGIN)
    suspend fun loginUser(
        @Body userRequest: UserRequest
    ): UserResponse

    @POST(Constants.API_PATH_REGISTER)
    suspend fun RegisterUser(
        @Body userRegisterRequest: UserRegisterRequest
    ): UserResponse

}