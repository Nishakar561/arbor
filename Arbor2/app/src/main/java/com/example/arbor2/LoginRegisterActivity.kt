package com.example.arbor2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Common.Token
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.Response
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import java.util.regex.Pattern

class LoginRegisterActivity : AppCompatActivity() {

    private lateinit var mService: RetrofitService
    private lateinit var splash: ImageView
    private lateinit var clover: ImageView
    private lateinit var textSplash: LinearLayout
    private lateinit var textHome: LinearLayout
    private lateinit var registerForm: LinearLayout
    private lateinit var form: ConstraintLayout
    private lateinit var loginForm: LinearLayout
    private lateinit var loginRegisterText: TextView
    private lateinit var yourAccountText: TextView
    private lateinit var forgetPassword: TextView
    private lateinit var dialog: android.app.AlertDialog

    private var backPressedTime: Long = 0
    private var onBackToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        mService = BaseURL.retrofitService
        splash = findViewById(R.id.splash)
        clover = findViewById(R.id.clover)
        textSplash = findViewById(R.id.textSplash)
        textHome = findViewById(R.id.textHome)
        registerForm = findViewById(R.id.register_form)
        form = findViewById(R.id.form)
        registerForm = findViewById(R.id.register_form)
        loginForm = findViewById(R.id.login_form)
        loginRegisterText = findViewById(R.id.login_register_text)
        yourAccountText = findViewById(R.id.your_account_text)
        forgetPassword = findViewById(R.id.forget_password)
        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()

        forgetPassword.setOnClickListener{
            startActivity(Intent(this,ForgetPasswordActivity::class.java))
        }

        val fromBottom: Animation = AnimationUtils.loadAnimation(this, R.anim.frombottom)

        registerForm.translationY = 2000f

        splash.animate().translationY(-1900f).setDuration(800).startDelay = 300
        clover.animate().alpha(0f).setDuration(800).startDelay = 600
        textSplash.animate().translationY(140f).alpha(0f).setDuration(800).startDelay = 600

        textHome.startAnimation(fromBottom)
        form.startAnimation(fromBottom)
    }

    fun registerOrLoginForm(view: View) {
        when (view.id) {
            R.id.toRegister -> {
                // Curtain down
                textHome.animate().alpha(0f).setDuration(800).startDelay = 0

                // Changes
                loginRegisterText.text = ("Register")
                yourAccountText.text = ("your account")
                registerForm.translationY = 0f
                loginForm.translationY = 2000f
                textHome.animate().alpha(1f).setDuration(800).startDelay = 200
            }

            R.id.toLogin -> {
                // Curtain down
                textHome.animate().alpha(0f).setDuration(800).startDelay = 0

                // Changes
                loginRegisterText.text = ("Login")
                yourAccountText.text = ("into your account")
                loginForm.translationY = 0f
                registerForm.translationY = 2000f
                textHome.animate().alpha(1f).setDuration(800).startDelay = 200
            }
        }
    }

    override fun onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            onBackToast?.cancel()
            super.onBackPressed()
            return
        } else{
            onBackToast = Toast.makeText(baseContext,"press again to exit",Toast.LENGTH_SHORT)
            onBackToast?.show()
        }

        backPressedTime = System.currentTimeMillis()
    }

    fun registerOrLogin(view: View) {
        when (view.id) {
            R.id.login -> {
                val mobileEditText: EditText = findViewById(R.id.mobile)
                val passwordEditText: EditText = findViewById(R.id.password)

                val mobile: String = mobileEditText.text.toString()
                val password: String = passwordEditText.text.toString()

                var isEmpty = false

                if(TextUtils.isEmpty(mobile.trim())){
                    mobileEditText.error = "Please enter mobile number"
                    mobileEditText.requestFocus()
                    isEmpty = true
                    // v.vibrate(100)
                }

                if(!TextUtils.isEmpty(mobile.trim())){
                    val pattern = Pattern.compile("^[6-9][0-9]{9}$")
                    val matcher = pattern.matcher(mobile)

                    if(!matcher.matches()){
                        mobileEditText.error = "Please enter a valid mobile number"
                        mobileEditText.requestFocus()
                        isEmpty = true
                    }
                }

                if(TextUtils.isEmpty(password.trim())){
                    passwordEditText.error = "Please enter password"
                    passwordEditText.requestFocus()
                    isEmpty = true
                    // v.vibrate(100)
                }

                if(!isEmpty){
                    dialog.show()

                    mService.login(mobile = mobile,password = password).enqueue(object: Callback<Response>{
                        override fun onFailure(call: Call<Response>, t: Throwable) {
                            dialog.dismiss()
                            Log.d("Failure",t.message!!.toString())
                        }

                        override fun onResponse(call: Call<Response>,  response: retrofit2.Response<Response>) {
                            dialog.dismiss()
                            if(response.body() != null){
                                val responseLogin = response.body() as Response

                                if(responseLogin.isSuccess == 1){
                                    val token = Token(this@LoginRegisterActivity)
                                    token.setUser(responseLogin.name,responseLogin.username,responseLogin.email,responseLogin.mobile,responseLogin.id,responseLogin.address)
                                    val intent = Intent(this@LoginRegisterActivity,MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else{
                                    Toast.makeText(this@LoginRegisterActivity,responseLogin.message,Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Log.d("LoginRes",response.body().toString())
                            }
                        }

                    })
                }

            }

            R.id.register -> {
                val nameEditText: EditText = findViewById(R.id.reg_name)
                val emailEditText: EditText = findViewById(R.id.reg_email)
                val mobileEditText: EditText = findViewById(R.id.reg_mobile)
                val passwordEditText: EditText = findViewById(R.id.reg_password)
                val addressEditText: EditText = findViewById(R.id.reg_address)

                val name: String = nameEditText.text.toString()
                val email: String = emailEditText.text.toString()
                val mobile: String = mobileEditText.text.toString()
                val password: String = passwordEditText.text.toString()
                val address: String = addressEditText.text.toString()
                var isEmpty = false

                if(TextUtils.isEmpty(name.trim())){
                    nameEditText.error = "Please enter a name"
                    nameEditText.requestFocus()
                    isEmpty = true
                }

                if(TextUtils.isEmpty(email.trim())){
                    emailEditText.error = "Please enter a email"
                    emailEditText.requestFocus()
                    isEmpty = true
                }

                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()){
                    emailEditText.error = "Please enter a valid email"
                    emailEditText.requestFocus()
                    isEmpty = true
                }

                if(TextUtils.isEmpty(mobile.trim())){
                    mobileEditText.error = "Please enter a mobile number"
                    mobileEditText.requestFocus()
                    isEmpty = true
                }

                if(!TextUtils.isEmpty(mobile.trim())){
                    val pattern = Pattern.compile("^[6-9][0-9]{9}$")
                    val matcher = pattern.matcher(mobile)

                    if(!matcher.matches()){
                        mobileEditText.error = "Please enter a valid mobile number"
                        mobileEditText.requestFocus()
                        isEmpty = true
                    }
                }

                if(TextUtils.isEmpty(password.trim())){
                    passwordEditText.error = "Please enter password"
                    passwordEditText.requestFocus()
                    isEmpty = true
                }

                if(TextUtils.isEmpty(address.trim())){
                    addressEditText.error = "Please enter address"
                    addressEditText.requestFocus()
                    isEmpty = true
                }

                if(!isEmpty){
                    dialog.show()
                    mService.register(name = name,email = email,mobile = mobile,password = password,address = address).enqueue(
                        object: Callback<Response>{
                            override fun onFailure(call: Call<Response>, t: Throwable) {
                                dialog.dismiss()
                                Log.d("Failure",t.message!!.toString())
                            }

                            override fun onResponse(
                                call: Call<Response>,
                                response: retrofit2.Response<Response>
                            ) {
                                dialog.dismiss()
                                val responseRegister = response.body() as Response

                                if(responseRegister.isSuccess == 1){
                                    val token = Token(this@LoginRegisterActivity)
                                    token.setUser(responseRegister.name,responseRegister.username,responseRegister.email,responseRegister.mobile,responseRegister.id,responseRegister.address)
                                    val intent = Intent(this@LoginRegisterActivity,MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                } else{
                                    Toast.makeText(this@LoginRegisterActivity,responseRegister.message,Toast.LENGTH_SHORT).show()
                                }
                            }

                        })
                }
            }
        }
    }
}
