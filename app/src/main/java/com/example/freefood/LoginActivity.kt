package com.example.freefood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val loginBTN = findViewById<ImageButton>(R.id.loginBTN)
        val loginEmail = findViewById<EditText>(R.id.loginEmail)
        val loginPass = findViewById<EditText>(R.id.loginPass)
        val gotoregister = findViewById<TextView>(R.id.gotoregister)
        auth = Firebase.auth

        loginBTN.setOnClickListener {
            val email = loginEmail.text.toString()
            val pass = loginPass.text.toString()

            if(email.isEmpty()){
                Toast.makeText(applicationContext, "Email Required!!", Toast.LENGTH_SHORT).show()
            }
            else if(pass.isEmpty()){
                Toast.makeText(applicationContext, "Password Required!!", Toast.LENGTH_SHORT).show()
            }
            else{
                //login
                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(applicationContext, "Login Successful!", Toast.LENGTH_SHORT).show()
                        finish()
                        startActivity(Intent(this, DashboardActivity::class.java))
                    }
                    else{
                        Toast.makeText(applicationContext, "Login Unsuccessful due to ${it.exception}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        gotoregister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser != null){
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }


}