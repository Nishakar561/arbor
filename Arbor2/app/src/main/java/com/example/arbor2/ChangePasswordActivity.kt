package com.example.arbor2

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Common.Token
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.Response
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var token: Token
    private lateinit var mService: RetrofitService
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        editText = findViewById(R.id.edit_text)
        button = findViewById(R.id.button)
        token = Token(this@ChangePasswordActivity)
        mService = BaseURL.retrofitService
        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()

        button.setOnClickListener {
            if(TextUtils.isEmpty(editText.text.toString())){
                editText.error = "Please enter password"
                editText.requestFocus()
            } else{
                changePassword(editText.text.toString())
            }
        }
    }

    private fun changePassword(password: String){
        dialog.show()
        if(button.text.toString() == "Check password"){
            mService.checkPassword(email = token.getEmail()!!,password = password).enqueue(object : Callback<Response>{
                override fun onFailure(call: Call<Response>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(this@ChangePasswordActivity,"${t.message}",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    dialog.dismiss()
                    if(response.body() != null){
                        val changeResponse: Response = response.body() as Response

                        if(changeResponse.isSuccess == 1){
                            editText.setText("")
                            editText.hint = ("Enter your new password")
                            button.text = ("Change password")
                        } else{
                            Toast.makeText(this@ChangePasswordActivity,changeResponse.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }
        else if(button.text.toString() == "Change password"){
            mService.updatePassword(email = token.getEmail()!!,password = password).enqueue(object : Callback<Response>{
                override fun onFailure(call: Call<Response>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(this@ChangePasswordActivity,"${t.message}",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    dialog.dismiss()
                    if(response.body() != null){
                        val changeResponse: Response = response.body() as Response

                        if(changeResponse.isSuccess == 1){
                            Toast.makeText(this@ChangePasswordActivity,changeResponse.message,Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@ChangePasswordActivity,ProfileActivity::class.java))
                            finish()
                        } else{
                            Toast.makeText(this@ChangePasswordActivity,changeResponse.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }
    }
}
