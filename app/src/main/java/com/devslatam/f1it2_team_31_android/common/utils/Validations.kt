package com.devslatam.f1it2_team_31_android.common.utils

import android.util.Patterns
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

/* 
* Project: f1it2-team-31-android
* From: com.devslatam.f1it2_team_31_android.common.utils
* Create by Ivan Barbosa on 27/05/2023 at 5:38 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*
*/

object Validations {
    fun validateFields(rootView: View, vararg textFields: TextInputLayout): Boolean {
        var isValid = true
        for (textField in textFields) {
            if (textField.editText?.text.toString().isEmpty()) {
                textField.error = "Campo Requerido"
                isValid = false
            } else {
                textField.error = null
            }
        }
        if (!isValid)
            Snackbar.make(rootView, "Campos Requeridos", Snackbar.LENGTH_SHORT).show()
        return isValid
    }

    fun isEmailValid(rootView: View, textField: TextInputLayout): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        val matcher = pattern.matcher(textField.editText?.text.toString())
        if (!matcher.matches()) {
            Snackbar.make(rootView, "Correo invalido", Snackbar.LENGTH_SHORT).show()
            textField.error = "Email Invalido"
            return false
        }
        return true
    }
}