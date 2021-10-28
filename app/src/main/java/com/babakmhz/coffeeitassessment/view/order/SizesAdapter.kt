package com.babakmhz.coffeeitassessment.view.order

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babakmhz.coffeeitassessment.data.model.device.Size
import com.babakmhz.coffeeitassessment.databinding.ItemSizeBinding

class SizesAdapter(
    private val context: Context,
    private val items: ArrayList<Size>,
    private val onItemClicked: (Size) -> Unit
) : RecyclerView.Adapter<SizesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSizeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Size) {
            binding.size = item
            binding.executePendingBindings()
            binding.lytContainer.setOnClickListener {
                selectItem(item)
                onItemClicked.invoke(item)
            }

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(types: List<Size>) {
        this.items.clear()
        this.items.addAll(types)
        notifyDataSetChanged()
    }

    private fun selectItem(selectedItem: Size) {
        for (item in items) {
            item.selected = item == selectedItem
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSizeBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}