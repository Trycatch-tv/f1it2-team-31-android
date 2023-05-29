package com.devslatam.f1it2_team_31_android.common.entities

/* 
* Project: f1it2-team-31-android
* From: com.devslatam.f1it2_team_31_android.common.entities
* Create by Ivan Barbosa on 27/05/2023 at 8:15 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
data class UserResponse(
    val id: Int,
    val name: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val role: String,
    val token: String
)
