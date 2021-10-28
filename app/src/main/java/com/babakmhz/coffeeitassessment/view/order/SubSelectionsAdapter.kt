package com.babakmhz.coffeeitassessment.view.order

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babakmhz.coffeeitassessment.data.model.device.Subselection
import com.babakmhz.coffeeitassessment.databinding.ItemSubSelectionBinding

class SubSelectionsAdapter(
    private val context: Context,
    private val items: ArrayList<Subselection>,
    private val onItemClicked: (Subselection) -> Unit
) : RecyclerView.Adapter<SubSelectionsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSubSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Subselection) {
            binding.subSelection = item
            binding.executePendingBindings()
            binding.lytContainer.setOnClickListener {
                selectItem(item)
                onItemClicked.invoke(item)
            }

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(types: List<Subselection>) {
        this.items.clear()
        this.items.addAll(types)
        notifyDataSetChanged()
    }

    private fun selectItem(selectedItem: Subselection) {
        for (item in items) {
            item.selected = item == selectedItem
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSubSelectionBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}