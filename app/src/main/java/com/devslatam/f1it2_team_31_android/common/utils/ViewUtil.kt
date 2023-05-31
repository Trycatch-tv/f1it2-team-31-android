package com.devslatam.f1it2_team_31_android.common.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.devslatam.f1it2_team_31_android.R

/* 
* Project: f1it2-team-31-android
* From: com.devslatam.f1it2_team_31_android.common.utils
* Create by Ivan Barbosa on 30/05/2023 at 8:13 p. m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
class ViewUtil(private val context: Context) {

    private var progressDialog: Dialog? = null

    fun showCustomProgressDialog(message: String) {
        progressDialog = Dialog(context)
        progressDialog?.setContentView(R.layout.custom_progress_dialog)
        progressDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog?.setCancelable(false)

        val messageTextView = progressDialog?.findViewById<TextView>(R.id.messageTextView)
        messageTextView?.text = message

        progressDialog?.show()
    }

    fun hideCustomProgressDialog() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    fun hideKeyboard(rootView: View){
        val inn = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inn.hideSoftInputFromWindow(rootView.windowToken, 0)
    }
}
