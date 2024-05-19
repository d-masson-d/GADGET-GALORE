package com.example.androidtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val sp = getSharedPreferences("TY", MODE_PRIVATE).edit()

        var Email: TextView = findViewById(R.id.SindP_svp)
        var Name: TextView = findViewById(R.id.Nick_text)
        var Password: TextView = findViewById(R.id.PasswordP_svb)
        var ButtonRegicter: Button = findViewById(R.id.registerButton)

        ButtonRegicter.setOnClickListener {
            if (Email.text.isEmpty() || !Email.text.contains("@")){
                Toast.makeText(this,"Fill in all the fields!!!", Toast.LENGTH_LONG).show()
            }
            else if(Name.text.isEmpty()){
                Toast.makeText(this,"Fill in all the fields!!!", Toast.LENGTH_LONG).show()
            }
            else if (Password.text.isEmpty() || Password.text.length < 8){
                Toast.makeText(this, "The password field must not be empty and cannot contain less than 8 characters!!", Toast.LENGTH_LONG).show()
            }
            else{
                var db = Firebase.firestore
                var user = hashMapOf(
                    "email" to Email.text.toString(),
                    "nickname" to Name.text.toString(),
                    "password" to Password.text.toString(),
                    "avatar" to "",
                    "phone" to ""
                )

                db.collection("users")
                    .add(user)
                    .addOnSuccessListener {
                        sp.putString("Email",Email.text.toString()).commit()
                        sp.putString("Nickname",Name.text.toString()).commit()
                        startActivities(
                            arrayOf(
                                Intent(this, LoginActivity2::class.java)
                            )
                        )
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"Error, try again later!!", Toast.LENGTH_LONG).show()
                    }
            }

        }
        var back_end: ImageButton = findViewById(R.id.back_btn)
        back_end.setOnClickListener(){
            startActivities(
                arrayOf(
                    Intent(this, MainActivity::class.java)
                )
            )
        }

    }
}