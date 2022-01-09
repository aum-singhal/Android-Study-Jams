package com.example.freefood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class DashboardActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference
    private val serveFragment = ServeFragment()
    private val searchFragment = SearchFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        auth = Firebase.auth
        db = FirebaseDatabase.getInstance().getReference("Service")
        val logout = findViewById<FloatingActionButton>(R.id.logout)
        val amenu = findViewById<BottomNavigationView>(R.id.actionmenu)

        val searchView = findViewById<SearchView>(R.id.searchlist)




        amenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.serve -> replaceFragment(serveFragment)
                R.id.search -> replaceFragment(searchFragment)
                else -> {
                    replaceFragment(serveFragment)
                }
            }
        }

        logout.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    private fun replaceFragment(fragment: Fragment): Boolean {
        val fragmentManager= supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentView, fragment)
        fragmentTransaction.commit()
        return true
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser == null){
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}