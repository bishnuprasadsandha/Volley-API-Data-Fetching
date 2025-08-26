package com.example.apidatafatching

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {

    private val url = "https://gorest.co.in/public/v2/users"
    private var userInfoItemList = arrayListOf<UserInfoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(url, { response ->

            val gson = GsonBuilder().create()
            val usersArray = gson.fromJson(response, Array<UserInfoItem>::class.java)

            userInfoItemList.clear()
            userInfoItemList.addAll(usersArray)

            Toast.makeText(this, userInfoItemList.toString(), Toast.LENGTH_SHORT).show()

        }, { error ->
            error.printStackTrace()
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        })

        requestQueue.add(stringRequest)
    }
}