package com.example.arbor2.Common

import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Retrofit.RetrofitClient
import com.paypal.android.sdk.payments.PayPalConfiguration

object BaseURL {

	// Put your base url in the var BASE_URL ex:  https://<your domain name>/arbor/
    const val BASE_URL = ""
	// Put your pay_pal client ID in the PAY_PAL_CLIENT_ID variable 
    private const val PAY_PAL_CLIENT_ID = ""
	// this secret code will be used for communication authentication put your code in th PAY_PAL_REQUEST_CODE variable
    const val PAY_PAL_REQUEST_CODE = 123

    val config:PayPalConfiguration? = PayPalConfiguration()
        .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
        .clientId(PAY_PAL_CLIENT_ID)

    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}