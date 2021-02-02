package com.example.arbor2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.Response
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var dialog: android.app.AlertDialog
    private lateinit var mService: RetrofitService
    private var otp: Int = 0
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        editText = findViewById(R.id.edit_text)
        button = findViewById(R.id.button)
        mService = BaseURL.retrofitService
        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()

        button.setOnClickListener {
            generate(editText.text.toString())
        }
    }

    private fun generate(string: String){
        if(button.text.toString() == "Generate OTP"){
            var isEmpty = false
            if(TextUtils.isEmpty(string)){
                editText.error = "Please enter a email"
                editText.requestFocus()
                isEmpty = true
            }

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(string.trim()).matches()){
                editText.error = "Please enter a valid email"
                editText.requestFocus()
                isEmpty = true
            }

            if(!isEmpty){
                dialog.show()
                mService.generateOTP(email = string).enqueue(object : Callback<Response>{
                    override fun onFailure(call: Call<Response>, t: Throwable) {
                        dialog.dismiss()
                        Toast.makeText(this@ForgetPasswordActivity,"${t.message}",Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<Response>,
                        response: retrofit2.Response<Response>
                    ) {
                        if(response.body() != null){
                            val responseOTP: Response = response.body() as Response

                            if(responseOTP.isSuccess == 1){
                                Toast.makeText(this@ForgetPasswordActivity,responseOTP.message,Toast.LENGTH_SHORT).show()
                                email = string
                                button.text = ("Verify OTP")
                                editText.setText("")
                                editText.hint = ("Enter OTP")
                                otp = responseOTP.id
                                editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password,0,0,0)
                                dialog.dismiss()
                            } else{
                                Toast.makeText(this@ForgetPasswordActivity,responseOTP.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                })

            }
        }

        else if(button.text.toString() == "Verify OTP"){
            if(TextUtils.isEmpty(string)){
                editText.error = "Please enter the OTP"
                editText.requestFocus()
            }

            if(!TextUtils.isEmpty(string)){
                if(string.toInt() != otp){
                    editText.error = "OTP didn't match"
                    editText.requestFocus()
                } else{
                    button.text = ("Update")
                    editText.setText("")
                    editText.hint = ("Enter new password")
                }
            }

        }

        else if(button.text.toString() == "Update"){
            dialog.show()
            mService.updatePassword(email = email, password = string).enqueue(object : Callback<Response>{
                override fun onFailure(call: Call<Response>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(this@ForgetPasswordActivity,"${t.message}",Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    dialog.dismiss()

                    if(response.body() != null){
                        val responseUpdate = response.body() as Response

                        if(responseUpdate.isSuccess == 1){
                            Toast.makeText(this@ForgetPasswordActivity,responseUpdate.message,Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@ForgetPasswordActivity,LoginRegisterActivity::class.java))
                            finish()
                        } else{
                            Toast.makeText(this@ForgetPasswordActivity,responseUpdate.message,Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })
        }
    }
}
