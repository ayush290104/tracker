package com.example.tracker

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class MainActivity2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var storage : FirebaseStorage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
   val storage = Firebase.storage("gs://tracker-a4f30.appspot.com")
        auth = Firebase.auth

var image:ImageView = findViewById(R.id.imageView2)
        var ishidden:Boolean = true
        val hide = findViewById<ImageButton>(R.id.imageButton5)
        var nametext:EditText = findViewById(R.id.editTextTextPersonName)
        var emailid : EditText = findViewById(R.id.email_id)
        var passwordtext: EditText = findViewById(R.id.password)
        val loginaccount = findViewById<Button>(R.id.create_account4)
        loginaccount.setOnClickListener(View.OnClickListener {

            val intent = Intent(this@MainActivity2,MainActivity3::class.java)
            startActivity(intent)


        })
        hide.setOnClickListener(View.OnClickListener {

            if(ishidden){
                hide.setImageResource(R.drawable.password23)
                passwordtext.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ishidden = false
            }else {
                passwordtext.transformationMethod = PasswordTransformationMethod.getInstance()

                hide.setImageResource(R.drawable.password13)
                ishidden = true
            }


        })


        val createAccount:Button = findViewById(R.id.create_account)

        createAccount.setOnClickListener(View.OnClickListener {
            var email = emailid.text.toString()
            var password = passwordtext.text.toString()
            var name = nametext.text.toString()
            var dp = image.drawable

            if (email==""||password==""){
                Toast.makeText(this,"enter all credentials", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(ContentValues.TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            var storageRef = storage.reference

                            val database = Firebase.database
                            val myRef = database.getReference("USERS")
                            val mountainImagesRef = storageRef.child("@drawable/images")
                            myRef.child(auth.currentUser?.uid.toString()).child("name").setValue(name)



                            user?.sendEmailVerification()?.addOnSuccessListener {
                                Toast.makeText(this@MainActivity2,"Please verify your email",Toast.LENGTH_SHORT).show()
                            }



                            updateUI(user)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                        }
                    }
            }
        })
    }

    private fun updateUI(user: FirebaseUser?) {

    val intent = Intent(this@MainActivity2, MainActivity3::class.java)
    startActivity(intent)


        }
}