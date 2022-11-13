package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.alRegister.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {

            val email = binding.email.text.toString()
            val pwd = binding.pwd.text.toString()
            val chckpwd = binding.confirmPwd.text.toString()

            if (email.isNotEmpty() && pwd.isNotEmpty() && chckpwd.isNotEmpty()) {
                if (pwd.equals(chckpwd)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener({
                        if (it.isSuccessful) {
                            val intent = Intent(this, SignIn::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    })
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }

            } else Toast.makeText(this, "Empty field are not allowed", Toast.LENGTH_SHORT).show()

        }


    }
}