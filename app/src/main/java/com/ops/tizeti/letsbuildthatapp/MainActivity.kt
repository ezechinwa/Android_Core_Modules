package com.ops.tizeti.letsbuildthatapp

import adaptors.MainAdapter
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview_main.layoutManager = LinearLayoutManager(this)
        //recyclerview_main.adapter = MainAdapter()

        fetchData()
    }
    fun fetchData(){
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to fetch data from server. ")
            }

            override fun onResponse(call: Call?, response: Response?) {
                  val body = response?.body()?.string()
//                    Toast.makeText(applicationContext,body,Toast.LENGTH_LONG).show()

                val gson = GsonBuilder().create()
               val homeFeed =  gson.fromJson(body, HomeFeed::class.java)
                runOnUiThread {
                    recyclerview_main.adapter = MainAdapter(homeFeed)
                }

            }
        })
    }


}

class HomeFeed(val videos : List<Video>)
class Video(val id: Int, val name : String, val link : String, val imageUrl: String, val numberOfViews: Int, val channel : Channel )
class Channel(val name : String , val profileImageUrl : String , val numberOfSubscribers : Int)


