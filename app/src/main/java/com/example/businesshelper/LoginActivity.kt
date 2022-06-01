package com.example.businesshelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        email=findViewById(R.id.AuthEmailAddress)
        password=findViewById(R.id.AuthTextPassword)
    }
    fun signInApp(view: View) {
        if(!email.text.isNullOrEmpty()&&!password.text.isNullOrEmpty())
        {
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
        }
        else
            Toast.makeText(applicationContext,"Заполните поля.", Toast.LENGTH_SHORT).show()
    }
    fun goToRegistration(view: View) {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }
}