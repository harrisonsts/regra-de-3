package com.example.regrade3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.regrade3.databinding.ItemViewBinding

class HistoricoAdapter : RecyclerView.Adapter<HistoricoAdapter.ViewHolder>() {

    private val asyncListDiffer: AsyncListDiffer<HistoricoItem> = AsyncListDiffer(this, DiffCallback)

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

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val item = itemList[position]
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun submitList(newList: List<HistoricoItem>){
        val oldSize = asyncListDiffer.currentList.size
        asyncListDiffer.submitList(newList)
        notifyItemRangeInserted(oldSize, newList.size - oldSize)
    }

    object DiffCallback : DiffUtil.ItemCallback<HistoricoItem>() {
        override fun areItemsTheSame(oldItem: HistoricoItem, newItem: HistoricoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HistoricoItem, newItem: HistoricoItem): Boolean {
            return oldItem == newItem
        }
    }
}