package com.example.apicalltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.apicalltest.classes.RecipesDataCollectionItem
import com.example.apicalltest.classes.RestEngine
import com.example.apicalltest.classes.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    val appId: String = "9f9ee2ec"
    val apiKey: String = "93ef30f07a4f979e4f5cf2fe6626bce7"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener(){v ->
        try{
                callServiceGetUsers()
            }catch (e: Exception){
            e.message?.let { Log.d("Error", it) }
            }

        }
    }

    private fun callServiceGetUsers() {
        val userService: UserService = RestEngine.getRestEngine().create(UserService::class.java)
        val result : Call<List<RecipesDataCollectionItem>> = userService.listRecipes("public","Chicken", appId, apiKey)

        result.enqueue(object: Callback<List<RecipesDataCollectionItem>>{
            override fun onFailure(call: Call<List<RecipesDataCollectionItem>>, t: Throwable) {
                Log.d("Response", "Error")
            }

            override fun onResponse(call: Call<List<RecipesDataCollectionItem>>, response: Response<List<RecipesDataCollectionItem>>) {
                Log.d("Response","OK - CODE: " + response.code() +"Message: "+ response.message())
                Log.d("response", response.body().toString())
            }
        })
    }

    fun callAPI (){
        Log.d("CALL API", "Me llamaron")
    }
}