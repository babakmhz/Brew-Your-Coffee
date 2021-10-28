package com.babakmhz.coffeeitassessment.view.overview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babakmhz.coffeeitassessment.data.model.device.Type
import com.babakmhz.coffeeitassessment.databinding.ItemOverviewBinding

class OverviewAdapter(
    private val context: Context,
    private val items: ArrayList<Type>,
    private val onEditClicked: (Type) -> Unit
) : RecyclerView.Adapter<OverviewAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemOverviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Type) {
            binding.type = item
            binding.executePendingBindings()
            binding.txtEdit.setOnClickListener {
                onEditClicked.invoke(item)
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemOverviewBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}