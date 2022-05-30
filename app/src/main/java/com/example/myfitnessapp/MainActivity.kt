package com.example.myfitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button3.setOnClickListener {

            when {
                TextUtils.isEmpty(weight_edit.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter Weight.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(height_edit.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@MainActivity,
                        "Please enter Height.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    val weight = weight_edit.text.toString().toDouble()
                    val height = height_edit.text.toString().toDouble()/3.281

                        val intent = Intent(this@MainActivity, BmiActivity::class.java)
                        intent.putExtra("bmi", calBMI(weight, height))
                        startActivity(intent)
                }
            }
        }
    }

    private fun calBMI(weight: Double, height: Double) = BigDecimal(weight/(height * height))
    .setScale(2, RoundingMode.HALF_EVEN).toDouble()
}