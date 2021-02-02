package com.example.arbor2

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arbor2.Adapter.CategoryAdapter
import com.example.arbor2.Common.BaseURL
import com.example.arbor2.Common.Token
import com.example.arbor2.Interface.RetrofitService
import com.example.arbor2.Model.Category
import com.example.arbor2.Model.CategoryItem
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),CategoryAdapter.OnItemClickLister {

    private lateinit var mService: RetrofitService
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var adapter: CategoryAdapter
    private lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {

        val token = Token(this)
        when(token.getUsername()){
            null -> {
                val intent = Intent(this@MainActivity,LoginRegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = BaseURL.retrofitService
        layoutManager = GridLayoutManager(this, 2)
        category_recycler_view.layoutManager = layoutManager
        val splash: ImageView = findViewById(R.id.splash)
        val clover: ImageView = findViewById(R.id.clover)
        val textSplash: LinearLayout = findViewById(R.id.textSplash)
        val textHome: LinearLayout = findViewById(R.id.textHome)
        val fromBottom: Animation = AnimationUtils.loadAnimation(this, R.anim.frombottom)
        val menus: RecyclerView = findViewById(R.id.category_recycler_view)


        textHome.alpha = 0f

        //getAllCategoryList()
        mService.getCategoryList().enqueue(object : Callback<Category> {
            override fun onFailure(call: Call<Category>, t: Throwable) {
                Toast.makeText(this@MainActivity,"${t.message}",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Category>, response: Response<Category>) {

                toggle = ActionBarDrawerToggle(this@MainActivity,drawerLayout, R.string.open, R.string.close)
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
                            val intent = Intent(this@MainActivity,ProfileActivity::class.java)
                            startActivity(intent)
                        }

                        R.id.cart->{
                            val intent = Intent(this@MainActivity,CartActivity::class.java)
                            startActivity(intent)
                        }

                        R.id.order->{
                            val intent = Intent(this@MainActivity,OrderActivity::class.java)
                            startActivity(intent)
                        }

                        R.id.history->{
                            val intent = Intent(this@MainActivity,ReturnActivity::class.java)
                            startActivity(intent)
                        }

                        R.id.logout->{
                            Toast.makeText(this@MainActivity,"Logout",Toast.LENGTH_SHORT).show()
                            token.logout()
                            val intent = Intent(this@MainActivity,LoginRegisterActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.putExtra("EXIT",true)
                            startActivity(intent)
                            finish()
                        }
                    }

                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }

                // Backend work
                val categoryList = response.body() as ArrayList<CategoryItem>
                adapter = CategoryAdapter(baseContext, categoryList,this@MainActivity)
                adapter.notifyDataSetChanged()
                category_recycler_view.adapter = adapter

                // animation
                splash.animate().translationY(-1900f).setDuration(800).startDelay = 300
                clover.animate().alpha(0f).setDuration(800).startDelay = 600
                textSplash.animate().translationY(140f).alpha(0f).setDuration(800).startDelay = 600
                textHome.alpha = 1f
                textHome.animation = fromBottom
                menus.animation = fromBottom
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(categoryId: Int,categoryName: String) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("categoryId",categoryId.toString())
        intent.putExtra("categoryName", categoryName)
        startActivity(intent)
    }
}
