package com.example.businesshelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password:EditText
    private  lateinit var repeatPassword:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        email = findViewById(R.id.registEmailAddress)
        password = findViewById(R.id.registPassword)
        repeatPassword = findViewById(R.id.registPasswordRepeat)
    }

    fun createAccount(view: View) {
        if(!email.text.isNullOrEmpty()&&!password.text.isNullOrEmpty()&&!repeatPassword.text.isNullOrEmpty())
        {
            if(password.text.toString()==repeatPassword.text.toString())
            {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(applicationContext,"Регистрация прошла успешно.",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else
                Toast.makeText(applicationContext,"Пароли не совподают.",Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(applicationContext,"Заполните поля.",Toast.LENGTH_SHORT).show()

    }
}