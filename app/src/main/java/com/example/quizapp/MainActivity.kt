package com.example.quizapp

import android.app.AlertDialog
import android.app.Dialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chooseAnswerOne()
        chooseAnswerTwo()
        resetBtnPressed()
        submitBtnPressed()
    }



    //Actions
    private fun chooseAnswerOne() {
        radioBtnGroubQ1.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.Q1A1) {
               score += 50
            }
        }
    }

    private fun chooseAnswerTwo() {
        radioBtnGroubQ2.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.Q2A2) {
                score += 50
            }
        }
    }

    private fun resetBtnPressed(){
        resetBtn.setOnClickListener {
            radioBtnGroubQ1.clearCheck()
            radioBtnGroubQ2.clearCheck()
            score = 0
        }
    }

    private fun submitBtnPressed(){
        submitBtn.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            var message = ""
            if (score == 50) {
                message =  "Congratulations! You submitted on current date and time, Your achieved 50%\n"
            } else if(score == 100) {
                message = "Congratulations! You submitted on current date and time, Your achieved 100%\n"
            } else {
                message = "YOU FAILED\n"
            }
            message += currentDate
            showCustomDialog(message)
        }
    }

    private fun showCustomDialog(message: String) {
        val li = LayoutInflater.from(this)
        val promptsView = li.inflate(R.layout.dialog_layout,null)
        var txtView = promptsView.findViewById<TextView>(R.id.textView) as TextView

        txtView.text = message
        val alertDialogBuilder = AlertDialog.Builder(this).setView(promptsView)
        alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton("OK"){dialog, id ->
                score = 0
                dialog.cancel()
            }
            .setNegativeButton("Reset") { dialog, id ->
                score = 0
                radioBtnGroubQ1.clearCheck()
                radioBtnGroubQ2.clearCheck()
                dialog.cancel()
            }
        // create alert dialog
        val alertDialog = alertDialogBuilder?.create()

        // show it
        alertDialog?.show()
    }


}