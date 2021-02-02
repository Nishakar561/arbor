package com.example.arbor2

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Common.Token
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.ItemDetails
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_data_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.roundToInt

class DataDetails : AppCompatActivity() {

    private lateinit var mService: RetrofitService
    private var price: Int = 0
    private var quantity: Int = 1
    var finalSize: String = ""
    private lateinit var id: String
    lateinit var sizes: ArrayList<String>
    lateinit var quantityText : TextView
    lateinit var plus : Button
    lateinit var minus : Button
    lateinit var realPrice: TextView
    lateinit var priceText: TextView
    lateinit var discount: TextView
    lateinit var image : ImageView
    lateinit var brand: TextView
    lateinit var details: TextView
    lateinit var stock : TextView
    lateinit var spinner: Spinner
    lateinit var item: ItemDetails
    private lateinit var addToCartButton: Button
    private lateinit var token: Token
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var dialog: android.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_details)

        token = Token(this@DataDetails)
        when(token.getUsername()){
            null -> {
                val intent = Intent(this@DataDetails,LoginRegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        val intent = intent
        id = intent.getStringExtra("id")!!

        image  = findViewById(R.id.image)
        brand = findViewById(R.id.brand)
        details = findViewById(R.id.details)
        stock = findViewById(R.id.stock)
        spinner = findViewById(R.id.spinner)
        quantityText = findViewById(R.id.quantity)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        mService = BaseURL.retrofitService
        realPrice= findViewById(R.id.real_price)
        priceText= findViewById(R.id.price)
        discount= findViewById(R.id.discount)
        addToCartButton = findViewById(R.id.add_to_cart)
        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()
        dialog.show()

        addToCartButton.setOnClickListener {
            sendDataToCart()
        }

        mService.getDataItemDetails("get_data_item_details.php?id=$id").enqueue(object: Callback<ItemDetails>{
            override fun onFailure(call: Call<ItemDetails>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@DataDetails,"${t.message}",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ItemDetails>, response: Response<ItemDetails>) {
                dialog.dismiss()

                toggle = ActionBarDrawerToggle(this@DataDetails,drawerLayout, R.string.open, R.string.close)
                drawerLayout.addDrawerListener(toggle)
                toggle.syncState()

                supportActionBar?.setDisplayHomeAsUpEnabled(true)

                val navigationView : NavigationView = findViewById(R.id.nav_view)
                val hView = navigationView.getHeaderView(0)
                val name : TextView = hView.findViewById(R.id.header_name)
                val email : TextView = hView.findViewById(R.id.header_email)
                name.text = token.getName()
                email.text = token.getEmail()

                navigationView.setNavigationItemSelectedListener {
                    when(it.itemId){
                        R.id.profile->{
                            val intentTo = Intent(this@DataDetails,ProfileActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.cart->{
                            val intentTo = Intent(this@DataDetails,CartActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.order->{
                            val intentTo = Intent(this@DataDetails,OrderActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.history->{
                            val intentTo = Intent(this@DataDetails,ReturnActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.logout->{
                            Toast.makeText(this@DataDetails,"Logout",Toast.LENGTH_SHORT).show()
                            token.logout()
                            val intentTo = Intent(this@DataDetails,LoginRegisterActivity::class.java)
                            intentTo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            intentTo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            intentTo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            intentTo.putExtra("EXIT",true)
                            startActivity(intentTo)
                            finish()
                        }
                    }

                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                Log.d("ResponseDetail: ",response.body().toString())
                item = response.body() as ItemDetails
                Log.d("ItemDetail: ",item.toString())

                // Data that doesn't need change
                Picasso.get().load(BaseURL.BASE_URL+item.image).into(image)
                brand.text = item.brand
                details.text = item.details

                // data that needs change

                // Quantity
                quantityText.text = ("Quantity: $quantity")
                stock.text = if(item.stock != 0){("${item.stock} In Stock")} else {("Out of stock")}

                //price, realPrice and discount
                if(item.discount == 0){
                    realPrice.text = ("${item.price}")
                    priceText.text = ""
                    discount.text = ""
                } else {
                    realPrice.text = ("${item.price}")
                    realPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    priceText.text = ("${item.price - (((item.price * item.discount)/100.0).roundToInt())}")
                    discount.text = ("${item.discount}")
                }

                sizes = item.sizes as ArrayList<String>

                if(sizes.isEmpty()){
                    spinner.isEnabled = false
                } else {
                    spinner.adapter = ArrayAdapter<String>(this@DataDetails,android.R.layout.simple_expandable_list_item_1, sizes)
                    spinner.onItemSelectedListener = object : AdapterView.OnItemClickListener,
                        AdapterView.OnItemSelectedListener {
                        override fun onItemClick(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            Toast.makeText(this@DataDetails,"Item got Selected",Toast.LENGTH_SHORT).show()
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            Toast.makeText(this@DataDetails,"Please select a size",Toast.LENGTH_SHORT).show()
                        }

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            finalSize = spinner.getItemAtPosition(position).toString()
                        }

                    }
                }
            }

        })
    } // end main

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun quantitySetter(view: View) {

        when(view as Button){
            plus->{
                if((quantity+1) <= item.stock){
                    quantity++
                    quantityText.text = ("Quantity: $quantity")

                    if(item.discount == 0){
                        realPrice.text = ("${item.price * quantity}")
                    } else {
                        realPrice.text = ("${item.price * quantity}")
                        priceText.text = ("${item.price * quantity}")
                    }

                } else{
                    Toast.makeText(this@DataDetails,"Going over the stock",Toast.LENGTH_SHORT).show()
                }
            }

            minus ->{
                if((quantity-1) > 0){
                    quantity--
                    quantityText.text = ("Quantity: $quantity")

                    if(item.discount == 0){
                        realPrice.text = ("${item.price * quantity}")
                    } else {
                        realPrice.text = ("${item.price * quantity}")
                        priceText.text = ("${item.price * quantity}")
                    }
                }
            }
        }
    }



    private fun sendDataToCart() {
        if(item.stock <= 0){
            Toast.makeText(this@DataDetails,"Item is out of stock",Toast.LENGTH_SHORT).show()

        } else {
            dialog.show()
            price = if(item.discount == 0){
                (realPrice.text.toString()).toInt()
            } else {
                (priceText.text.toString()).toInt()
            }

            val token = Token(this@DataDetails)

            mService.addToCart(username = token.getUsername()!!,item_id = id,item_size = finalSize,item_amount = quantity,price = price).enqueue(
                object: Callback<com.example.arbor2.Model.Response>{
                    override fun onFailure(
                        call: Call<com.example.arbor2.Model.Response>,
                        t: Throwable
                    ) {
                        dialog.dismiss()
                        Toast.makeText(this@DataDetails,"${t.message}",Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<com.example.arbor2.Model.Response>,
                        response: Response<com.example.arbor2.Model.Response>
                    ) {
                        dialog.dismiss()
                        if(response.body() != null){
                            val responseCart = response.body() as com.example.arbor2.Model.Response

                            if(responseCart.isSuccess == 1){
                                Toast.makeText(this@DataDetails,responseCart.message,Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            )
        }

    }

}
