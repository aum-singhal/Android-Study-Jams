package com.example.freefood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        val registerBTN = findViewById<ImageButton>(R.id.registerBTN)
        val registerEmail = findViewById<EditText>(R.id.registerEmail)
        val registerPass = findViewById<EditText>(R.id.registerPassword)
        val registerName = findViewById<EditText>(R.id.registerName)
        val gotologin = findViewById<TextView>(R.id.gotologin)
        auth = Firebase.auth

        registerBTN.setOnClickListener {
            val name = registerName.text.toString()
            val email = registerEmail.text.toString()
            val pass = registerPass.text.toString()

            if(name.isEmpty()){
                Toast.makeText(applicationContext, "Name Required!!", Toast.LENGTH_SHORT).show()
            }
            else if(email.isEmpty()){
                Toast.makeText(applicationContext, "Email Required!!", Toast.LENGTH_SHORT).show()
            }
            else if(pass.isEmpty()){
                Toast.makeText(applicationContext, "Password Required!!", Toast.LENGTH_SHORT).show()
            }
            else{
                //register
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(applicationContext, "Registered Successful!", Toast.LENGTH_SHORT).show()
                        finish()
                        startActivity(Intent(this, DashboardActivity::class.java))
                    }
                    else{
                        Toast.makeText(applicationContext, "Registration Unsuccessful due to ${it.exception}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        gotologin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}