package com.example.exercise2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.exercise2.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.math.BigDecimal
import java.text.DecimalFormat
import android.app.Activity
import android.content.Context
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.app.ComponentActivity.ExtraData
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.buttonCalculate.setOnClickListener { calculate(it) }
        binding.buttonReset.setOnClickListener { reset(it) }
    }

    private fun calculate(view: View) {
        val weight = binding.editTextWeight.text.toString().toDoubleOrNull()
        val height = binding.editTextHeight.text.toString().toDoubleOrNull()
         val myImage: ImageView = findViewById(R.id.imageViewProfile)
        val name: TextView = findViewById(R.id.textViewBMI)
        if (weight != null && height != null) {
            val bmi = weight / ((height/100) * (height/100))
            binding.textViewBMI.text = "BMI :" + roundOffDecimal(bmi)

            if(bmi < 18.5){
                myImage.setImageResource(R.drawable.under)

            }
            else if( bmi >= 18.5 && bmi <= 24.9){
                myImage.setImageResource(R.drawable.normal)

            }
            else if( bmi >= 25) {
                myImage.setImageResource(R.drawable.over)

            }


            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken,0)
        } else {
            binding.textViewBMI.text = "Please enter number to calculate"
        }


    }

    private fun reset(view: View){
        binding.editTextHeight.setText("")
        binding.editTextWeight.setText("")
        binding.textViewBMI.text = "BMI :"
        binding.imageViewProfile.setImageResource(R.drawable.empty)
    }

    fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }
}
