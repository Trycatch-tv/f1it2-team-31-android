package com.devslatam.f1it2_team_31_android.common.entities

/* 
* Project: f1it2-team-31-android
* From: com.devslatam.f1it2_team_31_android.common.entities
* Create by Ivan Barbosa on 30/05/2023 at 12:14 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
data class UserRegisterRequest(
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val password: String,
    val role: String
)
