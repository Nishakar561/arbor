package com.example.arbor2

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Common.Token
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.Response
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import java.util.regex.Pattern

class ProfileActivity : AppCompatActivity() {

    private lateinit var usernameTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var mobileEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var updateButton: Button
    private lateinit var changePasswordButton: Button
    private lateinit var token: Token
    private lateinit var mService: RetrofitService
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        nameEditText = findViewById(R.id.name)
        usernameTextView = findViewById(R.id.username)
        emailEditText = findViewById(R.id.email)
        mobileEditText = findViewById(R.id.mobile_no)
        addressEditText = findViewById(R.id.address)
        updateButton = findViewById(R.id.update)
        changePasswordButton = findViewById(R.id.change_password)
        mService = BaseURL.retrofitService
        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()


        token = Token(this)
        nameEditText.setText(token.getName())
        usernameTextView.text = token.getUsername()
        emailEditText.setText(token.getEmail())
        mobileEditText.setText(token.getMobile())
        addressEditText.setText(token.getAddress())

        changePasswordButton.setOnClickListener {
            startActivity(Intent(this@ProfileActivity, ChangePasswordActivity::class.java))
        }

        updateButton.setOnClickListener {
            updateValues()
        }
    }

    private fun updateValues() {
        var isEmpty = false
        val finalName: String = nameEditText.text.toString()
        val finalEmail: String = emailEditText.text.toString()
        val finalMobile: String = mobileEditText.text.toString()
        val finalAddress: String = addressEditText.text.toString()

        if (TextUtils.isEmpty(finalName.trim())) {
            nameEditText.error = "Please enter a name"
            nameEditText.requestFocus()
            isEmpty = true
        }

        if (TextUtils.isEmpty(finalEmail.trim())) {
            emailEditText.error = "Please enter a email"
            emailEditText.requestFocus()
            isEmpty = true
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(finalEmail.trim()).matches()) {
            emailEditText.error = "Please enter a valid email"
            emailEditText.requestFocus()
            isEmpty = true
        }

        if (TextUtils.isEmpty(finalMobile.trim())) {
            mobileEditText.error = "Please enter a mobile number"
            mobileEditText.requestFocus()
            isEmpty = true
        }

        if (!TextUtils.isEmpty(finalMobile.trim())) {
            val pattern = Pattern.compile("^[6-9][0-9]{9}$")
            val matcher = pattern.matcher(finalMobile)

            if (!matcher.matches()) {
                mobileEditText.error = "Please enter a valid mobile number"
                mobileEditText.requestFocus()
                isEmpty = true
            }
        }

        if (TextUtils.isEmpty(finalAddress)) {
            addressEditText.error = "Please enter address"
            addressEditText.requestFocus()
            isEmpty = true
        }

        if (!isEmpty) {
            dialog.show()
            if (finalEmail != token.getEmail() && finalMobile == token.getMobile()) {
                mService.updateProfileWithEmail(
                    username = token.getUsername()!!,
                    name = finalName,
                    email = finalEmail,
                    mobile = token.getMobile()!!,
                    address = finalAddress
                )
                    .enqueue(object : Callback<Response> {
                        override fun onFailure(call: Call<Response>, t: Throwable) {
                            dialog.dismiss()
                            Toast.makeText(this@ProfileActivity, "${t.message}", Toast.LENGTH_SHORT)
                                .show()
                        }

                        override fun onResponse(
                            call: Call<Response>,
                            response: retrofit2.Response<Response>
                        ) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                val responseProfile: Response = response.body() as Response

                                if (responseProfile.isSuccess == 1) {
                                    Toast.makeText(
                                        this@ProfileActivity,
                                        responseProfile.message,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    token.setUser(
                                        responseProfile.name,
                                        responseProfile.username,
                                        responseProfile.email,
                                        responseProfile.mobile,
                                        responseProfile.id,
                                        responseProfile.address
                                    )

                                    nameEditText.setText(token.getName())
                                    usernameTextView.text = token.getUsername()
                                    emailEditText.setText(token.getEmail())
                                    mobileEditText.setText(token.getMobile())
                                    addressEditText.setText(token.getAddress())
                                }
                            }
                        }

                    })
            }

            else if (finalEmail == token.getEmail() && finalMobile != token.getMobile()) {
                mService.updateProfileWithNumber(
                    username = token.getUsername()!!,
                    name = finalName,
                    email = token.getEmail()!!,
                    mobile = finalMobile,
                    address = finalAddress
                )
                    .enqueue(object : Callback<Response> {
                        override fun onFailure(call: Call<Response>, t: Throwable) {
                            dialog.dismiss()
                            Toast.makeText(this@ProfileActivity, "${t.message}", Toast.LENGTH_SHORT)
                                .show()
                        }

                        override fun onResponse(
                            call: Call<Response>,
                            response: retrofit2.Response<Response>
                        ) {
                            dialog.dismiss()

                            if (response.body() != null) {
                                val responseProfile: Response = response.body() as Response

                                if (responseProfile.isSuccess == 1) {
                                    Toast.makeText(
                                        this@ProfileActivity,
                                        responseProfile.message,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    token.setUser(
                                        responseProfile.name,
                                        responseProfile.username,
                                        responseProfile.email,
                                        responseProfile.mobile,
                                        responseProfile.id,
                                        responseProfile.address
                                    )

                                    nameEditText.setText(token.getName())
                                    usernameTextView.text = token.getUsername()
                                    emailEditText.setText(token.getEmail())
                                    mobileEditText.setText(token.getMobile())
                                    addressEditText.setText(token.getAddress())
                                }
                            }
                        }

                    })
            }

            else if (finalEmail != token.getEmail() && finalMobile != token.getMobile()){
                Toast.makeText(this@ProfileActivity, "1", Toast.LENGTH_SHORT).show()
                mService.updateProfile(
                    username = token.getUsername()!!,
                    name = finalName,
                    email = finalEmail,
                    mobile = finalMobile,
                    address = finalAddress
                )
                    .enqueue(object : Callback<Response> {
                        override fun onFailure(call: Call<Response>, t: Throwable) {
                            dialog.dismiss()
                            Toast.makeText(this@ProfileActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(
                            call: Call<Response>,
                            response: retrofit2.Response<Response>
                        ) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                val responseProfile: Response = response.body() as Response

                                if (responseProfile.isSuccess == 1) {
                                    Toast.makeText(
                                        this@ProfileActivity,
                                        responseProfile.message,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    token.setUser(
                                        responseProfile.name,
                                        responseProfile.username,
                                        responseProfile.email,
                                        responseProfile.mobile,
                                        responseProfile.id,
                                        responseProfile.address
                                    )

                                    nameEditText.setText(token.getName())
                                    usernameTextView.text = token.getUsername()
                                    emailEditText.setText(token.getEmail())
                                    mobileEditText.setText(token.getMobile())
                                    addressEditText.setText(token.getAddress())
                                }
                            }
                        }

                    })
            }

            else{
                mService.updateProfileValues(username = token.getUsername()!!,name = finalName,address = finalAddress)
                    .enqueue(object : Callback<Response>{
                        override fun onFailure(call: Call<Response>, t: Throwable) {
                            dialog.dismiss()
                            Toast.makeText(this@ProfileActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                        }

                        override fun onResponse(
                            call: Call<Response>,
                            response: retrofit2.Response<Response>
                        ) {
                            dialog.dismiss()
                            if (response.body() != null) {
                                val responseProfile: Response = response.body() as Response

                                if (responseProfile.isSuccess == 1) {
                                    Toast.makeText(
                                        this@ProfileActivity,
                                        responseProfile.message,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    token.setUser(
                                        responseProfile.name,
                                        responseProfile.username,
                                        responseProfile.email,
                                        responseProfile.mobile,
                                        responseProfile.id,
                                        responseProfile.address
                                    )

                                    nameEditText.setText(token.getName())
                                    usernameTextView.text = token.getUsername()
                                    emailEditText.setText(token.getEmail())
                                    mobileEditText.setText(token.getMobile())
                                    addressEditText.setText(token.getAddress())

                                    val intent = Intent(this@ProfileActivity,MainActivity::class.java)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    intent.putExtra("EXIT",true)
                                    startActivity(intent)
                                    finish()
                                }
                            }
                        }

                    })
            }

        }
    }
}
