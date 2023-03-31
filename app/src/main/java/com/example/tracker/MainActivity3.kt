package com.example.tracker

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity3 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        var ishidden:Boolean = true

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val hide = findViewById<ImageButton>(R.id.imageButton)




        auth = Firebase.auth
        val emailaddress : EditText = findViewById(R.id.editTextTextPersonName2)
        val passwordlogin : EditText = findViewById(R.id.editTextTextPassword)
        val signin : Button = findViewById(R.id.button)
        hide.setOnClickListener(View.OnClickListener {

            if(ishidden){
                hide.setImageResource(R.drawable.password23)
                passwordlogin.transformationMethod = HideReturnsTransformationMethod.getInstance()
                ishidden = false
            }else {
                passwordlogin.transformationMethod = PasswordTransformationMethod.getInstance()

                hide.setImageResource(R.drawable.password13)
                ishidden = true
            }


        })

        signin.setOnClickListener(View.OnClickListener {
            val email = emailaddress.text.toString()
            val password = passwordlogin.text.toString()
            if (email==""||password==""){
                Toast.makeText(this,"enter all credentials", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(ContentValues.TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            if(auth.currentUser?.isEmailVerified==true){
                            val intent = Intent(this,MainActivity4::class.java)
                            startActivity(intent)
                            finish()}
                            else{

                               Toast.makeText(this@MainActivity3,"didnt verify email",Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Invalid Credentials",
                                Toast.LENGTH_SHORT).show()

                        }
                    }
            }

        })

    }
}