package com.example.regrade3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.regrade3.databinding.ActivityMainBinding
import com.google.android.material.divider.MaterialDividerItemDecoration

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val historicoList = mutableListOf<HistoricoItem>()
    private lateinit var adapter: HistoricoAdapter

    private val viewModel: HistoricoListViewModel by viewModels {
        HistoricoListViewModel.Factory(Historico)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonCalcular.setOnClickListener { calcular(binding.switch1.isChecked) }
        binding.buttonLimpar.setOnClickListener { limpar() }
        binding.buttonLimparHistorico.setOnClickListener { limparListaHistorico() }
        binding.historicoRecyclerView.itemAnimator = DefaultItemAnimator()

        adapter = HistoricoAdapter()
        binding.historicoRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.historicoRecyclerView.adapter = adapter

        //adapter.submitList(historicoList)

        viewModel.stateOnceAndStream().observe(this) { state ->
            bindUiSate(state)
        }
    }

    private fun bindUiSate(state: HistoricoListUiState){
        adapter.submitList(state.historicoItemList)
    }

    private fun salvarHistorico(numberA: String, numberB: String, numberC: String, numberX: String, id: String){
        val novoItem = HistoricoItem(
            id = id,
            numberA = numberA,
            numberB = numberB,
            numberC = numberC,
            numberX = numberX
        )

        historicoList.add(0, novoItem)
        adapter.submitList(historicoList.toList())
        binding.historicoRecyclerView.scrollToPosition(0)
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

                // Mostra resultado na tela
                binding.textInputX.hint = resultadoString
                binding.textResultado.text = "Resultado = ${resultadoString}"
                binding.textResultado.visibility = View.VISIBLE

                // Salva no historico
                salvarHistorico(numberA, numberB, numberC, resultadoString,
                    System.currentTimeMillis().toString())

            } else{
                val resultado: Float = ((numberB.toFloat() * numberC.toFloat()) / numberA.toFloat())
                val resultadoString = String.format("%.2f", resultado)

                // Mostra resultado na tela
                binding.textInputX.hint = resultadoString
                binding.textResultado.text = "X = ${resultadoString}"
                binding.textResultado.visibility = View.VISIBLE

                // Salva no historico
                salvarHistorico(numberA, numberB, numberC, resultadoString,
                    System.currentTimeMillis().toString())
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

    private fun limparListaHistorico(){
        historicoList.clear()
        adapter.submitList(historicoList.toList())
    }
}