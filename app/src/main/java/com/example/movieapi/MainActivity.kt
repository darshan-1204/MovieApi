package com.example.movieapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        apiInterface.getData("504c15eb9fd2acefc91b639894d7c295")
            .enqueue(object : Callback<MovieModel> {
                override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {

                    var data = response.body()

//
//                    Log.e(TAG, "onResponse: ========" + data)

                    binding.rcvMovie.layoutManager = GridLayoutManager(this@MainActivity,2)
                    binding.rcvMovie.adapter = MovieAdapter(data?.results)
                }

                override fun onFailure(call: Call<MovieModel>, t: Throwable) {

                    Log.e(TAG, "onFailure: ===" + t.message )
                }


            })

    }


}