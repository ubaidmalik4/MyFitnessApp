package com.example.myfitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        textView3.setOnClickListener{
            onBackPressed()
        }

        button2.setOnClickListener {

            when {
                TextUtils.isEmpty(editTextTextEmailAddress.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter Email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(editTextTextPassword2.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter Password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val email: String = editTextTextEmailAddress.text.toString().trim { it <= ' ' }
                    val password: String = editTextTextPassword2.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    this@RegisterActivity,
                                    "You have been registered successfully.",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent =
                                    Intent(this@RegisterActivity, LoginActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
     }
