package com.example.androidtest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.example.androidtest.databinding.ActivityDetailsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvImage = intent.getIntExtra("rvImage", 0)
        val rvName = intent.getStringExtra("rvName")

        binding.menuDImage.setImageResource(rvImage)
        binding.menuDName.text = rvName

        binding.backHomeRv.setOnClickListener {
            finish()
        }

        val tiktok: ImageButton = findViewById(R.id.tiktok_id)
        val youtube: ImageButton = findViewById(R.id.youtube_id)
        val twich: ImageButton = findViewById(R.id.twich_id)
        val telegramm: ImageButton = findViewById(R.id.telegram_id)
        twich.setOnClickListener(){
            setupButtonClick("twich")
        }
        youtube.setOnClickListener(){
            setupButtonClick("youtube")
        }
        telegramm.setOnClickListener(){
            setupButtonClick("telegram")
        }
        tiktok.setOnClickListener(){
            setupButtonClick("tiktok")
        }

    }
    fun setupButtonClick(btnId:String){
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val databaseReference = firebaseDatabase.getReference(btnId)
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val uriString = snapshot.getValue(String::class.java)
                if(uriString != null){
                    val uri = Uri.parse(uriString)
                    val intent1 = Intent(Intent.ACTION_VIEW,uri)
                    startActivity(intent1)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}