package com.example.arbor2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arbor2.Adapter.DataAdapter
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Common.Token
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.Data
import com.example.arbor2.Model.DataItem
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataActivity : AppCompatActivity(),DataAdapter.OnItemClickListener {

    private var categoryId : String? = null
    private var subCategoryId: String? = null
    private var subCategoryName: String? = null
    private lateinit var mService: RetrofitService
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DataAdapter
    private lateinit var token: Token
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        token = Token(this@DataActivity)
        when(token.getUsername()){
            null -> {
                val intent = Intent(this@DataActivity,LoginRegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        //value came from CategoryActivity
        val intent = intent
        categoryId = intent.getStringExtra("categoryId")
        subCategoryId = intent.getStringExtra("subCategoryId")
        subCategoryName = intent.getStringExtra("subCategoryName")

        mService = BaseURL.retrofitService
        layoutManager = LinearLayoutManager(this)
        data_recycler_view.layoutManager = layoutManager

        val splash: ImageView = findViewById(R.id.splash)
        val clover: ImageView = findViewById(R.id.clover)
        val textSplash: LinearLayout = findViewById(R.id.textSplash)
        val textHome: LinearLayout = findViewById(R.id.textHome)
        val subHeading: TextView = findViewById(R.id.sub_heading)
        val fromBottom: Animation = AnimationUtils.loadAnimation(this, R.anim.frombottom)
        val menus: RecyclerView = findViewById(R.id.data_recycler_view)

        textHome.alpha = 0f
        subHeading.text = subCategoryName

        mService.getDataList("get_data.php?category_id=$categoryId&sub_category_id=$subCategoryId").enqueue(object : Callback<Data>{
            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(this@DataActivity,"Something went wrong",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                toggle = ActionBarDrawerToggle(this@DataActivity,drawerLayout, R.string.open, R.string.close)
                drawerLayout.addDrawerListener(toggle)
                toggle.syncState()

                supportActionBar?.setDisplayHomeAsUpEnabled(true)

                val navigationView : NavigationView = findViewById(R.id.nav_view)
                val hView = navigationView.getHeaderView(0)
                val name : TextView = hView.findViewById(R.id.header_name)
                val email : TextView = hView.findViewById(R.id.header_email)
                name.text = token.getName()
                email.text = token.getEmail()
                nav_view.setNavigationItemSelectedListener {
                    when(it.itemId){
                        R.id.profile->{
                            val intentTo = Intent(this@DataActivity,ProfileActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.cart->{
                            val intentTo = Intent(this@DataActivity,CartActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.order->{
                            val intentTo = Intent(this@DataActivity,OrderActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.history->{
                            val intentTo = Intent(this@DataActivity,ReturnActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.logout->{
                            Toast.makeText(this@DataActivity,"Logout",Toast.LENGTH_SHORT).show()
                            token.logout()
                            val intentTo = Intent(this@DataActivity,LoginRegisterActivity::class.java)
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


                // Adding data to dataList
                val dataList = response.body() as ArrayList<DataItem>

                // set ArrayList into RecyclerView
                adapter = DataAdapter(baseContext, dataList,this@DataActivity)
                adapter.notifyDataSetChanged()
                data_recycler_view.adapter = adapter

                // animation
                splash.animate().translationY(-1900f).setDuration(800).startDelay = 300
                clover.animate().alpha(0f).setDuration(800).startDelay = 600
                textSplash.animate().translationY(140f).alpha(0f).setDuration(800).startDelay = 600
                textHome.alpha = 1f
                textHome.startAnimation(fromBottom)
                menus.startAnimation(fromBottom)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(this@DataActivity,DataDetails::class.java)
        intent.putExtra("id",id.toString())
        startActivity(intent)
    }
}
