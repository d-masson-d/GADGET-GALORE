package com.example.androidtest

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var emailLogin: TextView = findViewById(R.id.Email_Login)
        var passwordLogin: TextView = findViewById(R.id.Password_Login)
        var buttonLogin: Button = findViewById(R.id.sindP_bvt)
        var buttonSingUp: Button = findViewById(R.id.sing_up_button)
        var sp = getSharedPreferences("TY", Context.MODE_PRIVATE)

        buttonLogin.setOnClickListener{
            var db = Firebase.firestore
            var df = false
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if(document.getString("email")== emailLogin.text.toString()) {
                            if (document.getString("password") == passwordLogin.text.toString()) {
                                df = true
                                sp.edit().putString("Email", emailLogin.text.toString()).commit()

                                startActivities(
                                    arrayOf(
                                        Intent(this, LoginActivity2::class.java)
                                    )
                                )
                            }
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "You entered incorrect information", Toast.LENGTH_LONG).show()
                }
            var h = Handler()
            h.postDelayed({
                if(df==false){
                    Toast.makeText(this, "Password or Email is incorrect", Toast.LENGTH_LONG).show()
                }
            },1600)
        }

            if (sp.getString("TY", "-9") != "-9") {
                startActivities(
                    arrayOf(
                        Intent(this, LoginActivity2::class.java)
                    )
                )
            }
                buttonSingUp.setOnClickListener {
                    startActivities(
                        arrayOf(
                            Intent(this, RegisterActivity::class.java)
                        )
                    )
            }
        }
    }