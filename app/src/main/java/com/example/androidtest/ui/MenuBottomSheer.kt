package com.example.androidtest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidtest.Adapters.PopularAgapter
import com.example.androidtest.Models.PopularModel
import com.example.androidtest.Models.SharedModel
import com.example.androidtest.R
import com.example.androidtest.databinding.FragmentMenuBottomSheerBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MenuBottomSheer : BottomSheetDialogFragment() {

    private lateinit var adapter: PopularAgapter
    private lateinit var menuList: ArrayList<PopularModel>
    private lateinit var binding: FragmentMenuBottomSheerBinding
    private lateinit var sharedModel: SharedModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBottomSheerBinding.inflate(inflater,container,false)
        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel::class.java)

        menuList = ArrayList()
        menuList.add(PopularModel(R.drawable.banner_1, "Iphone 15 Pro Max Gold", "1499$",1))
        menuList.add(PopularModel(R.drawable.banner_2, "Iphone 15 Pro Max White", "1399$",1))
        menuList.add(PopularModel(R.drawable.banner_3, "Iphone 15 Pro Max Black", "1399$",1))
        menuList.add(PopularModel(R.drawable.banner_4, "Iphone 15 Pro Blue", "1299$",1))
        menuList.add(PopularModel(R.drawable.banner_5, "Iphone 14 Yellow", "999$",1))
        menuList.add(PopularModel(R.drawable.banner_6, "Samsung Galaxy S24 Ultra Black", "1099$",1))
        menuList.add(PopularModel(R.drawable.banner_7, "Samsung Galaxy S24 Black", "799$",1))
        menuList.add(PopularModel(R.drawable.banner_8, "MacBook Air 13 Midnight Black", "1649$",1))
        menuList.add(PopularModel(R.drawable.banner_9, "iPad Pro 2024 Black", "2349$",1))
        menuList.add(PopularModel(R.drawable.banner_10, "Apple Watch Series 9 Midnight Black", "599$",1))
        menuList.add(PopularModel(R.drawable.banner_11, "Apple AirPods Pro 2", "299$",1))
        menuList.add(PopularModel(R.drawable.banner_1, "Iphone 15 Pro Max Gold", "1499$",1))
        menuList.add(PopularModel(R.drawable.banner_2, "Iphone 15 Pro Max White", "1399$",1))
        menuList.add(PopularModel(R.drawable.banner_3, "Iphone 15 Pro Max Black", "1399$",1))
        menuList.add(PopularModel(R.drawable.banner_4, "Iphone 15 Pro Blue", "1299$",1))
        menuList.add(PopularModel(R.drawable.banner_5, "Iphone 14 Yellow", "999$",1))
        menuList.add(PopularModel(R.drawable.banner_6, "Samsung Galaxy S24 Ultra Black", "1099$",1))
        menuList.add(PopularModel(R.drawable.banner_7, "Samsung Galaxy S24 Black", "799$",1))
        menuList.add(PopularModel(R.drawable.banner_8, "MacBook Air 13 Midnight Black", "1649$",1))
        menuList.add(PopularModel(R.drawable.banner_9, "iPad Pro 2024 Black", "2349$",1))
        menuList.add(PopularModel(R.drawable.banner_10, "Apple Watch Series 9 Midnight Black", "599$",1))
        menuList.add(PopularModel(R.drawable.banner_11, "Apple AirPods Pro 2", "299$",1))
        adapter = PopularAgapter(requireContext(), menuList)
        adapter.setSharedModel(sharedModel)


        binding.menuRV.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.menuRV.adapter = adapter

        searchMenu()
        return binding.root
    }
    private fun searchMenu(){
        binding.searchMenuItem.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                filteredList(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filteredList(newText)
                return true
            }

        })
    }

    private fun filteredList (input : String?){
        val filteredList = if (input.isNullOrEmpty()){
            menuList
        }else{
            menuList.filter { item ->
                item.getRvName().contains(input, ignoreCase = true)
            }
        }

        adapter.updateList(filteredList as ArrayList<PopularModel>)
    }


}