package com.example.regrade3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.regrade3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomCalcular.setOnClickListener { calcular(binding.switch1.isChecked) }
        binding.buttonLimpar.setOnClickListener { limpar() }
    }

    private fun calcular(inversa: Boolean){
        val numberA = binding.textInputA.editText?.text.toString()
        val numberB = binding.textInputB.editText?.text.toString()
        val numberC = binding.textInputC.editText?.text.toString()

        if (numberA == "" || numberB == "" || numberC == ""){
            Toast.makeText(
                applicationContext,
                "Preencha todos os valores!",
                Toast.LENGTH_LONG).show()
        }else {
            if(inversa){
                val resultado: Float = ((numberA.toFloat() * numberB.toFloat()) / numberC.toFloat())
                val resultadoString = String.format("%.2f", resultado)

                binding.textInputX.hint = resultadoString
                binding.textResultado.text = "Resultado = ${resultadoString}"
                binding.textResultado.visibility = View.VISIBLE
            } else{
                val resultado: Float = ((numberB.toFloat() * numberC.toFloat()) / numberA.toFloat())
                val resultadoString = String.format("%.2f", resultado)

                binding.textInputX.hint = resultadoString
                binding.textResultado.text = "X = ${resultadoString}"
                binding.textResultado.visibility = View.VISIBLE
            }
        }
    }

    private fun limpar(){
        binding.textInputA.editText?.setText("")
        binding.textInputB.editText?.setText("")
        binding.textInputC.editText?.setText("")
        binding.textInputX.hint = "X"
        binding.textResultado.visibility = View.INVISIBLE
    }
}