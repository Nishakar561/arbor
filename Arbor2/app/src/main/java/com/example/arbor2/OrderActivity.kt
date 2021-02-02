package com.example.arbor2

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arbor2.Adapter.OrderAdapter
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Common.Token
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.Order
import com.example.arbor2.Model.OrderItem
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderActivity : AppCompatActivity(),OrderAdapter.OnItemClickListener {

    private lateinit var orderRecyclerView: RecyclerView
    private lateinit var mService: RetrofitService
    private lateinit var adapter: OrderAdapter
    private lateinit var orderList: ArrayList<OrderItem>
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        orderRecyclerView = findViewById(R.id.order_recycler_view)
        mService = BaseURL.retrofitService
        layoutManager = LinearLayoutManager(this)
        orderRecyclerView.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()

        val token = Token(this)
        getOrderedItems(token.getUsername()!!)

    }

    private fun getOrderedItems(username: String){
        dialog.show()
        mService.getOrderItems(username = username).enqueue(object : Callback<Order>{
            override fun onFailure(call: Call<Order>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@OrderActivity,"${t.message}",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                if(response.body() != null){
                    dialog.dismiss()
                    orderList = response.body() as ArrayList<OrderItem>

                    if(orderList.isNotEmpty()){
                        adapter = OrderAdapter(baseContext,orderList,this@OrderActivity)
                        adapter.notifyDataSetChanged()
                        orderRecyclerView.adapter = adapter
                    } else{
                        val noItem: TextView = findViewById(R.id.no_item)
                        noItem.text = ("No item to show")
                    }
                }
            }

        })
    }

    override fun onItemClick(item_id: Int) {
        val intent = Intent(this@OrderActivity,DataDetails::class.java)
        intent.putExtra("Id",item_id.toString())
        startActivity(intent)
        finish()
    }

    override fun onButtonClick(id: Int, orderList: ArrayList<OrderItem>, position: Int) {
        dialog.show()
        mService.cancelItem(id = id).enqueue(object : Callback<com.example.arbor2.Model.Response>{
            override fun onFailure(call: Call<com.example.arbor2.Model.Response>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@OrderActivity,"${t.message}",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<com.example.arbor2.Model.Response>,
                response: Response<com.example.arbor2.Model.Response>
            ) {
                dialog.dismiss()
                val responseCancel = response.body() as com.example.arbor2.Model.Response

                if(responseCancel.isSuccess == 1){
                    Toast.makeText(this@OrderActivity,responseCancel.message,Toast.LENGTH_SHORT).show()
                    orderList.removeAt(position)
                    adapter.notifyDataSetChanged()

                    if(orderList.isEmpty()){
                        val noItem: TextView = findViewById(R.id.no_item)
                        noItem.text = ("No item to show")
                    }
                } else{
                    Toast.makeText(this@OrderActivity,responseCancel.message,Toast.LENGTH_SHORT).show()
                }
            }
        })

        this.orderList = orderList
    }
}
