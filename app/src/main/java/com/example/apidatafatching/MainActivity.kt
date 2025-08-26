package com.example.apidatafatching

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {

    private val url = "https://gorest.co.in/public/v2/users"
    private var userInfoItemList = arrayListOf<UserInfoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rev)

        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(url, { response ->

            val gson = GsonBuilder().create()
            val usersArray = gson.fromJson(response, Array<UserInfoItem>::class.java)

            userInfoItemList.clear()
            userInfoItemList.addAll(usersArray)

            // FIXED: Changed "userInfo" to "userInfoItemList"
            // FIXED: Corrected typo in "adopter" → should be "adapter"
            val adapter = Adapter(this, userInfoItemList)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter

            Toast.makeText(this, "Data Loaded", Toast.LENGTH_SHORT).show()

        }, { error ->
            error.printStackTrace()
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        })

        requestQueue.add(stringRequest)
    }
}
