package com.example.regrade3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.regrade3.databinding.ItemViewBinding

class HistoricoAdapter(private val itemList: List<HistoricoItem>) : RecyclerView.Adapter<HistoricoAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(historico: HistoricoItem){
            binding.textItemA.text = binding.root.context.getString(R.string.h_number_a, historico.numberA)
            binding.textItemB.text = binding.root.context.getString(R.string.h_number_b, historico.numberB)
            binding.textItemC.text = binding.root.context.getString(R.string.h_number_c, historico.numberC)
            binding.textItemX.text = binding.root.context.getString(R.string.h_number_x, historico.numberX)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }
}