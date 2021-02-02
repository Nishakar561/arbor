package com.example.arbor2

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arbor2.Adapter.ReturnAdapter
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Common.Token
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.Order
import com.example.arbor2.Model.OrderItem
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReturnActivity : AppCompatActivity(),ReturnAdapter.OnItemClickListener {

    private lateinit var returnRecyclerView: RecyclerView
    private lateinit var noItemTextView: TextView
    private lateinit var mService: RetrofitService
    private lateinit var returnList: ArrayList<OrderItem>
    private lateinit var adapter: ReturnAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_return)

        returnRecyclerView = findViewById(R.id.return_recycler_view)
        noItemTextView = findViewById(R.id.no_item)
        mService = BaseURL.retrofitService
        layoutManager = LinearLayoutManager(this)
        returnRecyclerView.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(false).setContext(this).build()

        val token = Token(this)

        getReturnItemList(token.getUsername()!!)

    }

    private fun getReturnItemList(username: String){
        dialog.show()
        mService.getReturnItems(username = username).enqueue(object : Callback<Order>{
            override fun onFailure(call: Call<Order>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@ReturnActivity,"${t.message}",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                if(response.body() != null)
                {
                    dialog.dismiss()
                    returnList = response.body() as ArrayList<OrderItem>

                    if(returnList.isNotEmpty()){
                        adapter = ReturnAdapter(baseContext,returnList,this@ReturnActivity)
                        adapter.notifyDataSetChanged()
                        returnRecyclerView.adapter = adapter
                    } else{
                        noItemTextView.text = ("No item to show")
                    }

                }
            }

        })
    }

    override fun onItemClick(item_id: Int) {
        val intent = Intent(this@ReturnActivity,DataDetails::class.java)
        intent.putExtra("id",item_id.toString())
        startActivity(intent)
        finish()
    }

    override fun onButtonClick(id: Int, returnList: ArrayList<OrderItem>, position: Int) {
        dialog.show()
        mService.returnItem(id = id).enqueue(object : Callback<com.example.arbor2.Model.Response>{
            override fun onFailure(call: Call<com.example.arbor2.Model.Response>, t: Throwable) {
                dialog.dismiss()
                Toast.makeText(this@ReturnActivity,"${t.message}",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<com.example.arbor2.Model.Response>,
                response: Response<com.example.arbor2.Model.Response>
            ) {
                dialog.dismiss()
                val returnResponse = response.body() as com.example.arbor2.Model.Response

                if(returnResponse.isSuccess == 1){
                    Toast.makeText(this@ReturnActivity,returnResponse.message,Toast.LENGTH_SHORT).show()
                    returnList.removeAt(position)
                    adapter.notifyDataSetChanged()

                    if(returnList.isEmpty()){
                        noItemTextView.text = ("No item to show")
                    } else{
                        Toast.makeText(this@ReturnActivity,returnResponse.message,Toast.LENGTH_SHORT).show()
                    }
                } else{
                    Toast.makeText(this@ReturnActivity,returnResponse.message,Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}
