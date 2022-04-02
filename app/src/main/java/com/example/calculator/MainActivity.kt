package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var equalsButton: Button
    private var operation: String = ""
    private var firstNumber: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.resultTextView = findViewById(R.id.resultTextView)
        this.equalsButton = findViewById(R.id.equalsbutton)
    }

    fun numberClick(view: View){
        if(view is Button){
            var textResult: Int
            var number = view.text.toString().toInt()
            if(!resultTextView.text.toString().contains(".")){
                textResult = if (resultTextView.text.toString() != "" && resultTextView.text.toString() != "-"){
                    resultTextView.text.toString().toInt()
                }else{
                    0
                }
                if(resultTextView.text.toString().startsWith("-")) {
                    resultTextView.text = resultTextView.text.toString() + number
                }
                else{
                    if(!resultTextView.text.toString().contains(".")){
                        resultTextView.text = (10 * textResult + number).toString()
                    }else{
                        resultTextView.text = resultTextView.text.toString() + number
                    }

                }
            }else{
                var number = view.text.toString().toInt()
                resultTextView.text = resultTextView.text.toString() + number
            }
        }
    }

    fun operationClick(view: View){
        if(view is Button){
            operation = view.text.toString()
            if(operation != "-"){
                firstNumber = resultTextView.text.toString().toDouble()
                resultTextView.text = ""
            }else{
                if(resultTextView.text.toString() == "0"){
                    resultTextView.text = "-"
                }else{
                    firstNumber = resultTextView.text.toString().toDouble()
                    resultTextView.text = ""
                }
            }

            if(operation == "√" || operation == "+/-"){
                equalsResult(equalsButton)
            }
        }
    }

    fun equalsResult(view:View){
        if(view is Button){
            if(operation == "*"){
                resultTextView.text = (firstNumber * resultTextView.text.toString().toDouble()).toString()
            }
            else if(operation == "/"){
                resultTextView.text = (firstNumber / resultTextView.text.toString().toDouble()).toString()
            }
            else if (operation == "+"){
                resultTextView.text = (firstNumber + resultTextView.text.toString().toDouble()).toString()
            }
            else if(operation == "-"){
                if((resultTextView.text.toString() != "0") || resultTextView.text.toString() != ""){
                    resultTextView.text = (firstNumber - resultTextView.text.toString().toDouble()).toString()
                }else{resultTextView.text = "-"}

            }
            else if (operation == "%"){
                resultTextView.text = (firstNumber / 100 * resultTextView.text.toString().toDouble()).toString()
            }
            else if (operation == "√"){
                if(firstNumber >= 0){
                    resultTextView.text = sqrt(firstNumber).toString()
                }

            }
            else if (operation == "+/-"){
                if (firstNumber.toString() != "" || firstNumber.toString() != "0" || firstNumber.toString() != "0.0"){
                    resultTextView.text = ((-1) * firstNumber).toString()
                }else{resultTextView.text = "0"}
            }else{resultTextView.text = "Error"}
            if (resultTextView.text.toString().endsWith(".0")){resultTextView.text = resultTextView.text.toString().dropLast(2)}
        }
    }
    fun clearAll(view: View){
        if(view is Button){
            resultTextView.text = "0"
            firstNumber = 0.0
        }
    }
    fun addDot(view: View){
        if (view is Button){
            if(!resultTextView.text.toString().contains(".")){
                resultTextView.text = resultTextView.text.toString() + "."
            }
        }
    }
    fun deleteChar(view: View){
        if (view is Button){
            if (!(resultTextView.text.toString().length == 1)){
                if(!(resultTextView.text.toString().dropLast(1).endsWith("."))){
                    resultTextView.text = resultTextView.text.toString().dropLast(1)
                }else{
                    resultTextView.text = resultTextView.text.toString().dropLast(2)
                }

            }else{resultTextView.text = "0"}

        }
    }
}
