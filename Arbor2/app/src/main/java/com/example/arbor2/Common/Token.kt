package com.example.arbor2.Common

import android.content.Context
import android.content.SharedPreferences

class Token(context: Context) {

    private val preferenceName = "username"
    private val id = "id"
    private val username = "username"
    private val name = "name"
    private val email = "email"
    private val mobile = "mobile"
    private val address = "address"

    private val token: SharedPreferences = context.getSharedPreferences(preferenceName,Context.MODE_PRIVATE)


    fun setUser(nameU:String, usernameU: String,emailU: String, mobileU: String, idU: Int, addressU: String){
        val editor = token.edit()
            editor.apply{
                putInt(id, idU)
                putString(username,usernameU)
                putString(name,nameU)
                putString(email,emailU)
                putString(mobile,mobileU)
                putString(address,addressU)
            }.apply()
    }

    fun getId() : Int{
        return token.getInt(id,0)
    }

    fun getName(): String?{
        return token.getString(name,null)
    }

    fun getUsername() : String?{
        return token.getString(username,null)
    }

    fun getEmail() : String?{
        return token.getString(email,null)
    }

    fun getMobile() : String?{
        return token.getString(mobile,null)
    }

    fun getAddress() : String?{
        return token.getString(address,null)
    }

    fun logout() {
        val editor = token.edit()
        editor.clear()
        editor.apply()
    }
}