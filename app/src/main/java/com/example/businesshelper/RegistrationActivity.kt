package com.example.businesshelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.businesshelper.data.Account
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegistrationActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password:EditText
    private  lateinit var repeatPassword:EditText
    private lateinit var database: DatabaseReference


    private fun writeNewUser(password: String, email: String) {
        val id = database.key
        val user = Account(id,email,password)
        database.push().setValue(user)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        database = FirebaseDatabase.getInstance().getReference("users")

        email = findViewById(R.id.registEmailAddress)
        password = findViewById(R.id.registPassword)
        repeatPassword = findViewById(R.id.registPasswordRepeat)
    }

    fun createAccount(view: View) {
        if(!email.text.isNullOrEmpty()&&!password.text.isNullOrEmpty()&&!repeatPassword.text.isNullOrEmpty())
        {
            if(password.text.toString()==repeatPassword.text.toString())
            {
                try {
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(
                            email.text.toString(),
                            password.text.toString()
                        )
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {

                                writeNewUser(
                                    password.text.toString(),
                                    email.text.toString()
                                )

                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)

                                Toast.makeText(
                                    applicationContext,
                                    "Регистрация прошла успешно.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                }
                catch(e: Exception)
                {
                    Toast.makeText(applicationContext,e.message.toString(),Toast.LENGTH_LONG).show()
                }

            }
            else
                Toast.makeText(applicationContext,"Пароли не совподают.",Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(applicationContext,"Заполните поля.",Toast.LENGTH_SHORT).show()

    }
}