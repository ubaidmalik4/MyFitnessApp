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
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        textView1.setOnClickListener{
            startActivity(Intent( this@LoginActivity, RegisterActivity::class.java))
        }

        button.setOnClickListener {
            when {
                TextUtils.isEmpty(email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter Email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(editTextTextPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter Password.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val email: String = email.text.toString().trim { it <= ' ' }
                    val password: String = editTextTextPassword.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {

                                Toast.makeText(
                                    this@LoginActivity,
                                    "You have been logged in successfully.",
                                    Toast.LENGTH_SHORT
                                ).show()

                                val intent =
                                    Intent(this@LoginActivity, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id",
                                             FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
             }
          }

    override fun onBackPressed() {
        val exitIntent = Intent(Intent.ACTION_MAIN)
        exitIntent.addCategory(Intent.CATEGORY_HOME)
        exitIntent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(exitIntent)
         }
       }