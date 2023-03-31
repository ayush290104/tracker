package com.example.tracker

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
val signup = findViewById<Button>(R.id.signup)
        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity,MainActivity3::class.java)
            startActivity(intent)
            Toast.makeText(this@MainActivity,"Taking you to login page",Toast.LENGTH_SHORT).show()


        })
        signup.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity,MainActivity2::class.java)
startActivity(intent)
Toast.makeText(this@MainActivity,"Taking you to signup page",Toast.LENGTH_SHORT).show()


        })

    }
    public override fun onStart() {
        super.onStart()
        auth = Firebase.auth

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            Toast.makeText(this@MainActivity,"hiii",Toast.LENGTH_SHORT).show()
            var intent2 = Intent(this,MainActivity4::class.java)
            startActivity(intent2)
            finish()

        }
    }
}