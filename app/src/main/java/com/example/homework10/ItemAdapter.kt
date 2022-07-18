package com.example.homework10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.homework10.databinding.ItemsLayoutBinding

class ItemAdapter() : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private lateinit var data: MutableList<ItemData>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ItemViewHolder(
        ItemsLayoutBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val temp = data[position]
        holder.image.setImageResource(temp.image)
        holder.price.text = temp.price
        holder.tittle.text = temp.title
    }

    override fun getItemCount() = data.size
    fun setData(newList: MutableList<ItemData>){
        if(!this::data.isInitialized){
            data = newList
            return
        }
        val diffUtil = MyDiffUtil(data, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        data = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ItemViewHolder(binding: ItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.image
        val tittle = binding.tittle
        val price = binding.price
    }
}