package com.example.tracker

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class MainActivity4 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var list:ArrayList<users>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

val reset:Button = findViewById(R.id.button2)


        reset.setOnClickListener(View.OnClickListener {

            Firebase.auth.signOut()
            val intent = Intent(this,MainActivity2::class.java)
            startActivity(intent)
            finish()

        })



        val recyclerView:RecyclerView = findViewById(R.id.recylerview)
         list = arrayListOf()
        val adapter:CustomAdapter = CustomAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        auth = Firebase.auth


        val database = Firebase.database
        val myRef = FirebaseDatabase.getInstance().getReference("USERS")
        myRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                 if (snapshot.exists()){
                     for(datasnapshot in snapshot.children){
                         val user = datasnapshot.getValue(users::class.java)
                         if(!list.contains(user)){
                             list.add(user!!)
                         }
                     }
            recyclerView.adapter = CustomAdapter(list)

                 }


            }

            override fun onCancelled(error: DatabaseError) {
               Toast.makeText(this@MainActivity4,"hellllooooooooo",Toast.LENGTH_SHORT).show()

            }


        })


}}