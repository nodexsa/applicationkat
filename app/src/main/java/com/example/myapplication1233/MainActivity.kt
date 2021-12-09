package com.example.myapplication1233

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextTextPassword: EditText
    private lateinit var edittextpasswordgameoreba: EditText
    private lateinit var buttonregistracia: Button
    private var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        registerListeners()
    }
    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextTextPassword = findViewById(R.id.editTextTextPassword)
        edittextpasswordgameoreba = findViewById(R.id.edittextpasswordgameoreba)

        buttonregistracia = findViewById(R.id.buttonregistracia)
    }

    private fun registerListeners() {
        buttonregistracia.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextTextPassword.text.toString()
            val password2 = edittextpasswordgameoreba.text.toString()


            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Empty~!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password == password2 && password.length > 9 && password.contains("[0-9]".toRegex()) && password.contains("[a-z]".toRegex()) && email.matches(emailPattern.toRegex()) ){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Registration completed successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}