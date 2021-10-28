package com.example.parsinglocaljson_w5_d5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.parsinglocaljson_w5_d5.databinding.ActivityMainBinding
import org.json.JSONArray
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeBinding()
        initializeRecycler()
        requestJsonData()
    }

    private lateinit var binding: ActivityMainBinding
    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private lateinit var adapter: Recycler
    private lateinit var messages: ArrayList<Image>
    private fun initializeRecycler() {
        messages = ArrayList()
        adapter = Recycler(messages,this)
        binding.rvMain.adapter = adapter
        binding.rvMain.layoutManager = LinearLayoutManager(this)
    }

    private fun requestJsonData(){
        var json: String
        try {
            val inputStream: InputStream = assets.open("data.json")
            json = inputStream.bufferedReader().use { it.readText() }
            var jsonArray = JSONArray(json)
            for(i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val title = jsonObject.getString("title")
                val url = jsonObject.getString("url")
                messages.add(Image(title, url))
            }
            adapter.notifyDataSetChanged()
        }
        catch (e: Exception){
            Log.d("MAIN", "ISSUE IS: $e")
        }
    }
}