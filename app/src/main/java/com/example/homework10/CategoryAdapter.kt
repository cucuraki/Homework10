package com.example.homework10

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginStart
import androidx.recyclerview.widget.RecyclerView
import com.example.homework10.databinding.CategoryLayoutBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private val dataList by lazy {
        mutableListOf<CategoryData>()
    }
    var lastSelected = 0
    private lateinit var mListener: OnItemsClickListener

    fun setDataAtIndex(index: Int, background: Int) {
        dataList[index].color = background
    }

    fun getCategory(position: Int, context: Context): Categories =
        when(dataList[position].text){
            context.getString(R.string.c4) -> Categories.CATEGORY1
            context.getString(R.string.c5) -> Categories.CATEGORY2
            context.getString(R.string.c6) -> Categories.CATEGORY3
            context.getString(R.string.c2) -> Categories.PARTY
            context.getString(R.string.c3) -> Categories.CAMPING
            else -> Categories.DEFAULT
         }


    fun setData(categoryNames: MutableList<String>) {
        dataList.addAll(categoryNames.map { it -> CategoryData(it, R.drawable.category_button) })
        if (categoryNames.size != 0)
            dataList[0] = CategoryData(categoryNames[0], R.drawable.category_backgroung_green)
    }

    fun setOnClickListener(listener: OnItemsClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(CategoryLayoutBinding.inflate(LayoutInflater.from(parent.context)))


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.name.text = dataList[position].text
        holder.name.setBackgroundResource(dataList[position].color)
    }

    override fun getItemCount() = dataList.size


    inner class CategoryViewHolder(binding: CategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.text

        init {
            if (!this@CategoryAdapter::mListener.isInitialized)
                mListener = OnItemsClickListener {}
            name.setOnClickListener {
                mListener.onClick(adapterPosition)
            }
        }
    }

    fun interface OnItemsClickListener {
        fun onClick(position: Int)
    }
}