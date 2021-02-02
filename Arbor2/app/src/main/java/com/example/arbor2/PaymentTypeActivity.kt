package com.example.arbor2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Common.Token
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.BundleItems
import com.example.arbor2.Model.CartItem
import com.example.arbor2.Model.Response
import com.google.gson.Gson
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import java.math.BigDecimal

class PaymentTypeActivity : AppCompatActivity() {

    private lateinit var payPalButton: Button
    private lateinit var cashOnDeliveryButton: Button
    private lateinit var totalPriceTextView: TextView
    private lateinit var cartItemList: ArrayList<CartItem>
    private lateinit var mService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_type)

        payPalButton = findViewById(R.id.pay_pal)
        cashOnDeliveryButton = findViewById(R.id.cash_on_delivery)
        totalPriceTextView = findViewById(R.id.total_price)
        mService = BaseURL.retrofitService

        val intent = intent.getBundleExtra("bundle")

        @Suppress("UNCHECKED_CAST")
        cartItemList = intent?.getSerializable("cartItemList") as ArrayList<CartItem>

        var totalPrice= 0.0

        for (i in cartItemList.indices){
            totalPrice += cartItemList[i].price
        }

        totalPriceTextView.text = ("Total price: $totalPrice")

        cashOnDeliveryButton.setOnClickListener {
            val paymentMethod = "cash_on_delivery"
            val token = Token(this@PaymentTypeActivity)
            val bundle = BundleItems(token.getUsername()!!,cartItemList,paymentMethod)
            val cartItemList = JSONObject(Gson().toJson(bundle))

            orderItems(cartItemList)

            val intentPayPal = Intent(this,PayPalService::class.java)
            intentPayPal.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,BaseURL.config)
            startService(intentPayPal)
        }

        payPalButton.setOnClickListener {

            val payment = PayPalPayment(BigDecimal.valueOf(totalPrice),"USD","Arbor Shopping app",PayPalPayment.PAYMENT_INTENT_SALE)
            val intentPay = Intent(this@PaymentTypeActivity,PaymentActivity::class.java)
            intentPay.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,BaseURL.config)
            intentPay.putExtra(PaymentActivity.EXTRA_PAYMENT,payment)
            startActivityForResult(intentPay,BaseURL.PAY_PAL_REQUEST_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == BaseURL.PAY_PAL_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                val paymentMethod = "PayPal"
                val token = Token(this@PaymentTypeActivity)
                val bundle = BundleItems(token.getUsername()!!,cartItemList,paymentMethod)
                val cartItemList = JSONObject(Gson().toJson(bundle))
                orderItems(cartItemList)
            }
        }
    }

    override fun onDestroy() {
        stopService(Intent(this@PaymentTypeActivity,PayPalService::class.java))
        super.onDestroy()
    }



    private fun orderItems(bundle: JSONObject){
        mService.orderItems(bundle).enqueue(object: Callback<Response>{
            override fun onFailure(call: Call<Response>, t: Throwable) {
                Toast.makeText(this@PaymentTypeActivity,"Hello: ${t.message}",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {

                if(response.body() != null){
                    val responsePayment = response.body() as Response

                    if(responsePayment.isSuccess == 1){
                        Toast.makeText(this@PaymentTypeActivity,responsePayment.message,Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@PaymentTypeActivity,OrderActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        })
    }
}
