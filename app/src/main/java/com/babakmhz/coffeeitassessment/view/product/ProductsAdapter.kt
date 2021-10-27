package com.babakmhz.coffeeitassessment.view.product

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babakmhz.coffeeitassessment.data.model.device.Type
import com.babakmhz.coffeeitassessment.databinding.ItemProductBinding

class ProductsAdapter(
    private val context: Context,
    private val items: ArrayList<Type>,
    private val onItemClicked: (Type) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Type) {
            binding.repo = item
            binding.executePendingBindings()
            binding.lytContainer.setOnClickListener {
                onItemClicked.invoke(item)
            }

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(types:List<Type>){
        this.items.clear()
        this.items.addAll(types)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}