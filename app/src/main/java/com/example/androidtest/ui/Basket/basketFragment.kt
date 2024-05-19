package com.example.androidtest.ui.Basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtest.Adapters.BuyAdapter
import com.example.androidtest.Adapters.PopularAgapter
import com.example.androidtest.Models.PopularModel
import com.example.androidtest.Models.SharedModel
import com.example.androidtest.R
import com.example.androidtest.databinding.FragmentBasket2Binding


class basketFragment : Fragment() {


    private lateinit var binding: FragmentBasket2Binding
    private lateinit var sharedModel: SharedModel


    private lateinit var adapter: BuyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasket2Binding.inflate(inflater,container,false)

        sharedModel = ViewModelProvider(requireActivity()).get(SharedModel ::class.java)


        adapter = BuyAdapter(requireContext(), sharedModel.cartItem.value ?: ArrayList())

        binding.cartRV.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRV.adapter = adapter

        return binding.root
    }
}


