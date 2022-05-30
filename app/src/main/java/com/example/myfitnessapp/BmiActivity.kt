package com.example.myfitnessapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_login.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class BmiActivity : AppCompatActivity() {

    private var mInterstitialAd: InterstitialAd? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        var flag: Boolean
        val bmi = intent.getDoubleExtra("bmi", -1.0)

        if(bmi==-1.0){
         containerR.visibility= View.GONE
        }

        else {
            bmi_val.text = bmi.toString()

            if(bmi<18.5) {
                bmi_val.setTextColor(ContextCompat.getColor(this,R.color.teal_700))
                bmi_status.text ="You are underweight"
                bmi_status.setTextColor(ContextCompat.getColor(this,R.color.teal_700))
                bmi_message.text="Want to gain weight? Choose below."
            }

            else if(bmi>23){
                bmi_val.setTextColor(ContextCompat.getColor(this,R.color.teal_200))
                bmi_status.text ="You are overweight"
                bmi_status.setTextColor(ContextCompat.getColor(this,R.color.teal_200))
                bmi_message.text="Want to loose weight? Choose below."
            }

            else {
                bmi_val.setTextColor(ContextCompat.getColor(this,R.color.purple_700))
                bmi_status.text ="Your BMI is in healthy range"
                bmi_status.setTextColor(ContextCompat.getColor(this,R.color.purple_700))
                bmi_message.text="Want to get shredded? Choose below."
            }
          }

        loadInterAd()

        meals_button.setOnClickListener {
            flag = true
            showInterAd(flag)
        }

        workout_button.setOnClickListener {
            flag = false
            showInterAd(flag)
        }

        back_to_login.setOnClickListener{
            val intent = Intent(this@BmiActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
      }

    private fun showInterAd(flag: Boolean) {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback(){

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()

                    if(flag){
                        val intent = Intent(this@BmiActivity, DietActivity::class.java)
                        startActivity(intent)
                    }

                    else{
                        val intent = Intent(this@BmiActivity, WorkoutActivity::class.java)
                        startActivity(intent)
                    }

                    mInterstitialAd=null
                    loadInterAd()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                }
            }

            mInterstitialAd?.show(this)
        }

        else {
            if(flag){
                val intent = Intent(this@BmiActivity, DietActivity::class.java)
                startActivity(intent)
            }

            else{
                val intent = Intent(this@BmiActivity, WorkoutActivity::class.java)
                startActivity(intent)

            }
        }



    }

    private fun loadInterAd() {
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })
      }
    }





