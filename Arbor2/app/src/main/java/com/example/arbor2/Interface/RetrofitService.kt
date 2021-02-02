package com.example.arbor2.Interface

import com.example.arbor2.Model.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @GET("get_categories.php")
    fun getCategoryList(): Call<Category>

    @GET
    fun getSubCategoryList(@Url url: String) : Call<SubCategory>

    @GET
    fun getDataList(@Url url:String) : Call<Data>

    @GET
    fun getDataItemDetails(@Url url: String) : Call<ItemDetails>

    @POST("add_user.php")
    @FormUrlEncoded
    fun register(@Field("name") name: String,
                 @Field("email") email: String,
                 @Field("mobile") mobile: String,
                 @Field("password") password: String,
                 @Field("address") address: String
    ) : Call<Response>

    @POST("login.php")
    @FormUrlEncoded
    fun login(@Field("mobile") mobile: String,
              @Field("password") password: String
              ) : Call<Response>

    @POST("add_to_cart.php")
    @FormUrlEncoded
    fun addToCart(@Field("username") username: String,
                  @Field("item_id") item_id: String,
                  @Field("item_size") item_size: String,
                  @Field("item_amount") item_amount: Int,
                  @Field("price") price: Int
                  ) : Call<Response>


    @POST("get_cart_items.php")
    @FormUrlEncoded
    fun getCartItems(@Field( "username") username: String) : Call<Cart>

    @POST("remove_from_cart.php")
    @FormUrlEncoded
    fun deleteFromCart(@Field( "id") id: Int) : Call<Response>

    @POST("order.php")
    @FormUrlEncoded
    fun orderItems(@Field("cartItemList") cartItemList: JSONObject) : Call<Response>

    @POST("get_order_items.php")
    @FormUrlEncoded
    fun getOrderItems(@Field("username") username: String) : Call<Order>

    @POST("cancel_order.php")
    @FormUrlEncoded
    fun cancelItem(@Field("id") id: Int) : Call<Response>

    @POST("get_return_items.php")
    @FormUrlEncoded
    fun getReturnItems(@Field("username") username: String) : Call<Order>

    @POST("return_item.php")
    @FormUrlEncoded
    fun returnItem(@Field("id") id: Int): Call<Response>

    @POST("send_mail.php")
    @FormUrlEncoded
    fun generateOTP(@Field("email") email: String) : Call<Response>

    @POST("check_password.php")
    @FormUrlEncoded
    fun checkPassword(@Field("email") email: String,
                      @Field("password") password: String
                      ) : Call<Response>

    @POST("update_password.php")
    @FormUrlEncoded
    fun updatePassword(@Field("email") email: String,
                       @Field("password") password: String
                       ) : Call<Response>

    @POST("update_profile.php")
    @FormUrlEncoded
    fun updateProfile(@Field("username") username: String,
                      @Field("name") name: String,
                      @Field("email") email: String,
                      @Field("mobile") mobile: String,
                      @Field("address") address: String
                      ) : Call<Response>

    @POST("update_profile_with_email.php")
    @FormUrlEncoded
    fun updateProfileWithEmail(@Field("username") username: String,
                               @Field("name") name: String,
                               @Field("email") email: String,
                               @Field("mobile") mobile: String,
                               @Field("address") address: String
                               ) : Call<Response>

    @POST("update_profile_with_number.php")
    @FormUrlEncoded
    fun updateProfileWithNumber(@Field("username") username: String,
                                @Field("name") name: String,
                                @Field("email") email: String,
                                @Field("mobile") mobile: String,
                                @Field("address") address: String
                                ) : Call<Response>

    @POST("update_profile_values.php")
    @FormUrlEncoded
    fun updateProfileValues(@Field("username") username: String,
                            @Field("name") name: String,
                            @Field("address") address: String
                            ) : Call<Response>
}