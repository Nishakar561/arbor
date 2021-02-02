package com.example.arbor2

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arbor2.Adapter.CartAdapter
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Common.Token
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.Cart
import com.example.arbor2.Model.CartItem
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class CartActivity : AppCompatActivity(),CartAdapter.OnItemClickListener {

    private lateinit var mService: RetrofitService
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: CartAdapter
    private lateinit var cartItemList: ArrayList<CartItem>
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var orderButton: Button
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        mService = BaseURL.retrofitService
        layoutManager = LinearLayoutManager(this)
        cartRecyclerView = findViewById(R.id.cart_recycler_view)
        orderButton = findViewById(R.id.order_button)
        orderButton.alpha = 0f
        orderButton.isEnabled = false
        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()

        cartRecyclerView.layoutManager = layoutManager
        val token = Token(this)
        val username = token.getUsername()

        orderButton.setOnClickListener {
            orderItem()
        }

        dialog.show()


        mService.getCartItems(username= "$username").enqueue(object: Callback<Cart>{
            override fun onFailure(call: Call<Cart>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@CartActivity, "Failure",Toast.LENGTH_SHORT).show()
                Log.d("Response:",t.message!!.toString())
            }

            override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
                dialog.dismiss()
                Log.d("Cart",response.body().toString())
                cartItemList = response.body() as ArrayList<CartItem>
                if(cartItemList.isNotEmpty()){

                    adapter = CartAdapter(baseContext,cartItemList,this@CartActivity)
                    adapter.notifyDataSetChanged()
                    cartRecyclerView.adapter = adapter

                    orderButton.alpha = 1f
                    orderButton.isEnabled = true

                } else{
                    val noItem: TextView = findViewById(R.id.no_item)
                    noItem.text = ("No item to show")
                }
            }

        })
    }

    override fun onItemClick(itemId: Int) {

        val intent = Intent(this@CartActivity,DataDetails::class.java)
        intent.putExtra("id", itemId.toString())
        startActivity(intent)
        finish()
    }

    override fun onButtonClick(id: Int,cartItemList: ArrayList<CartItem>,position: Int) {
        dialog.show()
        mService.deleteFromCart(id=id).enqueue(object: Callback<com.example.arbor2.Model.Response>{
            override fun onFailure(call: Call<com.example.arbor2.Model.Response>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@CartActivity,t.message,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<com.example.arbor2.Model.Response>,
                response: Response<com.example.arbor2.Model.Response>
            ) {
                dialog.dismiss()
                if(response.body() != null){
                    val responseCart = response.body() as com.example.arbor2.Model.Response

                    if(responseCart.isSuccess == 1){
                        Toast.makeText(this@CartActivity,responseCart.message,Toast.LENGTH_SHORT).show()
                        cartItemList.removeAt(position)
                        adapter.notifyDataSetChanged()
                    }

                    if(cartItemList.isEmpty()){
                        orderButton.isEnabled = false
                        orderButton.alpha = 0f
                        val noItem: TextView = findViewById(R.id.no_item)
                        noItem.text = ("No item to show")
                    }
                }
            }
        })

        this.cartItemList = cartItemList
    }

    private fun orderItem(){
        val intent = Intent(this@CartActivity,PaymentTypeActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("cartItemList", cartItemList as Serializable)
        intent.putExtra("bundle",bundle)
        startActivity(intent)
        finish()
    }
}
