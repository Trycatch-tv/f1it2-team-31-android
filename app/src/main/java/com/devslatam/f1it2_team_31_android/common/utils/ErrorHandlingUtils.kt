package com.devslatam.f1it2_team_31_android.common.utils

import com.devslatam.f1it2_team_31_android.R
import com.devslatam.f1it2_team_31_android.common.entities.ErrorResponse
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/* 
* Project: f1it2-team-31-android
* From: com.devslatam.f1it2_team_31_android.common.utils
* Create by Ivan Barbosa on 28/05/2023 at 9:31 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
object ErrorHandlingUtils {

    fun parseErrorMessage(errorBody: String?): String {
        if (errorBody.isNullOrBlank()) {
            return R.string.error_server_response.toString()
        }
        return try {
            val gson = Gson()
            val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
            errorResponse.error
        } catch (e: JsonSyntaxException) {
            R.string.error_server_response.toString()
        }
    }

    fun getErrorMessage(exception: Exception): String {
        return when (exception) {
            is SocketTimeoutException -> R.string.error_timeout.toString()
            is UnknownHostException -> R.string.error_unknown_host.toString()
            is IOException -> R.string.error_connection.toString()
            is JsonParseException -> R.string.error_parse_json.toString()
            else -> R.string.error_unknown.toString()
        }
    }
}