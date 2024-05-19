package com.example.androidtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.androidtest.databinding.ActivityLogin2Binding

class LoginActivity2 : AppCompatActivity() {

    lateinit var binding: ActivityLogin2Binding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        binding.bottomNavView.setupWithNavController(navController)


        val sp = getSharedPreferences("TY", Context.MODE_PRIVATE)
        sp.edit().putString("TY", "9").apply()
       // val userEmail: TextView = findViewById(R.id.UserEmailId)
        //val userName: TextView = findViewById(R.id.UserNameId)

        //userEmail.text = sp.getString("Email", "No connection")
       // userName.text = sp.getString("nickname", "No connection")

        /*var logout: ImageButton = findViewById(R.id.logout)
        logout.setOnClickListener(){
            sp.edit().putString("TY", "-9").apply()
            Toast.makeText(this,"You are logged out of your account!!!",Toast.LENGTH_LONG).show()
            startActivities(
                arrayOf(
                    Intent(this, MainActivity::class.java)
                )
            )
        }*/


    }


    override fun onBackPressed() {
        super.onBackPressed()
    }
}