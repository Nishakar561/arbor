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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arbor2.Adapter.SubCategoryAdapter
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Common.Token
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.SubCategory
import com.example.arbor2.Model.SubCategoryItem
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_category.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity(),SubCategoryAdapter.OnItemClickListener {

    private lateinit var mService: RetrofitService
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: SubCategoryAdapter
    private lateinit var toggle : ActionBarDrawerToggle
    private var categoryId: String? = null
    private var categoryName: String? = null
    private lateinit var token: Token

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)


        token = Token(this@CategoryActivity)
        when(token.getUsername()){
            null -> {
                val intent = Intent(this@CategoryActivity,LoginRegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        val intent = intent
        categoryId = intent.getStringExtra("categoryId")
        categoryName = intent.getStringExtra("categoryName")

        mService = BaseURL.retrofitService
        layoutManager = GridLayoutManager(this, 2)
        sub_category_recycler_view.layoutManager = layoutManager
        val splash: ImageView = findViewById(R.id.splash)
        val clover: ImageView = findViewById(R.id.clover)
        val textSplash: LinearLayout = findViewById(R.id.textSplash)
        val textHome: LinearLayout = findViewById(R.id.textHome)
        val subHeading: TextView = findViewById(R.id.sub_heading)
        val fromBottom: Animation = AnimationUtils.loadAnimation(this, R.anim.frombottom)
        val menus: RecyclerView = findViewById(R.id.sub_category_recycler_view)

        textHome.alpha = 0f
        subHeading.text = categoryName

        mService.getSubCategoryList("get_sub_categories.php?id=$categoryId").enqueue(object: Callback<SubCategory>{
            override fun onFailure(call: Call<SubCategory>, t: Throwable) {
                Toast.makeText(this@CategoryActivity,"Something went wrong",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SubCategory>, response: Response<SubCategory>) {

                toggle = ActionBarDrawerToggle(this@CategoryActivity,drawerLayout, R.string.open, R.string.close)
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
                            val intentTo = Intent(this@CategoryActivity,ProfileActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.cart->{
                            val intentTo = Intent(this@CategoryActivity,CartActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.order->{
                            val intentTo = Intent(this@CategoryActivity,OrderActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.history->{
                            val intentTo = Intent(this@CategoryActivity,ReturnActivity::class.java)
                            startActivity(intentTo)
                        }

                        R.id.logout->{
                            Toast.makeText(this@CategoryActivity,"Logout",Toast.LENGTH_SHORT).show()
                            token.logout()
                            val intentTo = Intent(this@CategoryActivity,LoginRegisterActivity::class.java)
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

                //adding items to ArrayList
                val subCategoryList = response.body() as ArrayList<SubCategoryItem>

                //set ArrayList into RecyclerView with all items
                adapter = SubCategoryAdapter(baseContext, subCategoryList,this@CategoryActivity)
                adapter.notifyDataSetChanged()
                sub_category_recycler_view.adapter = adapter

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


    override fun onItemClick(subCategoryId: Int, subCategoryName: String) {
        val intent = Intent(this@CategoryActivity,DataActivity::class.java)
        intent.putExtra("categoryId", categoryId)
        intent.putExtra("subCategoryId", subCategoryId.toString())
        intent.putExtra("subCategoryName",subCategoryName)
        startActivity(intent)
    }
}
