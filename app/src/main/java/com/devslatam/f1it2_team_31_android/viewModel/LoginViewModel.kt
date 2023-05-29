package com.devslatam.f1it2_team_31_android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devslatam.f1it2_team_31_android.common.entities.UserRequest
import com.devslatam.f1it2_team_31_android.common.entities.UserResponse
import com.devslatam.f1it2_team_31_android.common.utils.ErrorHandlingUtils
import com.devslatam.f1it2_team_31_android.model.LoginModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

/* 
* Project: f1it2-team-31-android
* From: com.devslatam.f1it2_team_31_android.viewModel
* Create by Ivan Barbosa on 27/05/2023 at 8:01 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class LoginViewModel : ViewModel() {
    private val loginModel = LoginModel()
    private val result = MutableLiveData<UserResponse>()
    private val msgSnackbar = MutableLiveData<String>()
    private val loaded = MutableLiveData<Boolean>()

    fun getResult(): LiveData<UserResponse> = result

    fun getMsgSnackbar() = msgSnackbar

    fun isLoaded() = loaded

    fun postUserLogin(userRequest: UserRequest) {
        viewModelScope.launch {
            try {
                loaded.value = false
                val apiResponse = loginModel.postUserLogin(userRequest)
                result.value = apiResponse
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                val errorMessage = ErrorHandlingUtils.parseErrorMessage(errorBody)
                msgSnackbar.value = errorMessage
            } catch (e: Exception) {
                msgSnackbar.value = ErrorHandlingUtils.getErrorMessage(e)
            } finally {
                loaded.value = true
            }
        }
    }
}