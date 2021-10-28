package com.babakmhz.coffeeitassessment.view.order

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.babakmhz.coffeeitassessment.data.model.device.Extra
import com.babakmhz.coffeeitassessment.data.model.device.Subselection
import com.babakmhz.coffeeitassessment.databinding.ItemExtrasBinding

class ExtrasAdapter(
    private val context: Context,
    private val items: ArrayList<Extra>,
    private val onSubSelected:(Subselection)->Unit
) : RecyclerView.Adapter<ExtrasAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ItemExtrasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Extra) {
            binding.extra = item
            binding.executePendingBindings()
            binding.rclSubSelections.apply {
                setHasFixedSize(true)
                adapter = SubSelectionsAdapter(context,
                    item.subselections as ArrayList<Subselection>
                ) {
                    onSubSelected.invoke(it)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExtrasBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}