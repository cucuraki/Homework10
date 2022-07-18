package com.example.homework10


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val categories: RecyclerView by lazy {
        binding.categories
    }
    private val categoryData: MutableList<String> by lazy {
        mutableListOf(
            getString(R.string.c1),
            getString(R.string.c2),
            getString(R.string.c3),
            getString(R.string.c4),
            getString(R.string.c5),
            getString(R.string.c6)
        )
    }
    private val myAdapter: CategoryAdapter by lazy {
        val adapt = CategoryAdapter()
        adapt.setData(categoryData)
        adapt
    }
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var itemsData: MutableList<ItemData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoriesInitialization()
        itemsInitialization()

        myAdapter.setOnClickListener {
            onClickHighLite(it)
            filterByCategory(it)
        }
    }

    private fun categoriesInitialization() {
        categories.adapter = myAdapter
        categories.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }

    private fun onClickHighLite(position: Int) {
        myAdapter.setDataAtIndex(position, R.drawable.category_backgroung_green)
        myAdapter.setDataAtIndex(myAdapter.lastSelected, R.drawable.category_button)
        myAdapter.notifyItemChanged(position)
        myAdapter.notifyItemChanged(myAdapter.lastSelected)
        myAdapter.lastSelected = position
    }

    private fun filterByCategory(position: Int) {
        val category = myAdapter.getCategory(position, this)
        if(category == Categories.DEFAULT){
            itemAdapter.setData(itemsData)
            return
        }
        val tempData = itemsData.filter { it -> it.category == myAdapter.getCategory(position, this) }
        itemAdapter.setData(tempData.toMutableList())
    }

    private fun itemsInitialization() {
        itemsData = mutableListOf(
            ItemData(
                1,
                R.drawable.image,
                getString(R.string.title) + "1",
                getString(R.string.price1),
                Categories.CATEGORY1
            ),
            ItemData(
                2,
                R.drawable.image2,
                getString(R.string.title) + "2",
                getString(R.string.price2),
                Categories.CATEGORY2
            ),
            ItemData(
                3,
                R.drawable.image,
                getString(R.string.title) + "3",
                getString(R.string.price3),
                Categories.CAMPING
            ),
            ItemData(
                4,
                R.drawable.image,
                getString(R.string.title) + "4",
                getString(R.string.price1),
                Categories.PARTY
            ),
            ItemData(
                5,
                R.drawable.image2,
                getString(R.string.title) + "5",
                getString(R.string.price1),
                Categories.CATEGORY3
            ),
            ItemData(
                6,
                R.drawable.image3,
                getString(R.string.title) + "6",
                getString(R.string.price1),
                Categories.CAMPING
            )
        )
        itemAdapter = ItemAdapter()
        itemAdapter.setData(itemsData)
        binding.items.adapter = itemAdapter
        binding.items.layoutManager = GridLayoutManager(this, 2)
    }
}